package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.dataobject.order.CompanyOrder;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.*;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.CollectionOrderRepository;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.repository.config.SystemConfigRepository;
import edu.xpu.buckmoo.repository.order.CompanyOrderRepository;
import edu.xpu.buckmoo.repository.order.MemberOrderRepository;
import edu.xpu.buckmoo.repository.order.PartInfoRepository;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.PayNotifyCallback;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class PayNotifyCallbackImpl implements PayNotifyCallback {

    private final PartInfoService partInfoService;
    private final PartInfoRepository partInfoRepository;
    private final MemberOrderRepository memberOrderRepository;
    private final CompanyOrderRepository companyOrderRepository;
    private final CompanyInfoRepository companyInfoRepository;
    private final CollectionOrderRepository collectionOrderRepository;
    private final SystemConfigRepository systemConfigRepository;

    public PayNotifyCallbackImpl(PartInfoService partInfoService,
                                 PartInfoRepository partInfoRepository,
                                 MemberOrderRepository memberOrderRepository,
                                 CompanyOrderRepository companyOrderRepository,
                                 CompanyInfoRepository companyInfoRepository,
                                 CollectionOrderRepository collectionOrderRepository,
                                 SystemConfigRepository systemConfigRepository) {
        this.partInfoService = partInfoService;
        this.partInfoRepository = partInfoRepository;
        this.memberOrderRepository = memberOrderRepository;
        this.companyOrderRepository = companyOrderRepository;
        this.companyInfoRepository = companyInfoRepository;
        this.collectionOrderRepository = collectionOrderRepository;
        this.systemConfigRepository = systemConfigRepository;
    }

    @Override
    public void payNotify(String notifyData) {
        String orderId = null;
        //开始解析微信服务器返回的xml文档,文档示例：
        /*
         * <xml>
         * 	<appid><![CDATA[wxb39b42f5fd93c167]]></appid>
         * 	<bank_type><![CDATA[CFT]]></bank_type>
         * 	<cash_fee><![CDATA[1]]></cash_fee>
         * 	<fee_type><![CDATA[CNY]]></fee_type>
         * 	<is_subscribe><![CDATA[Y]]></is_subscribe>
         * 	<mch_id><![CDATA[1545797881]]></mch_id>
         * 	<nonce_str><![CDATA[k9aLTeA4QwEhk3cB]]></nonce_str>
         * 	<openid><![CDATA[oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk]]></openid>
         * 	<out_trade_no><![CDATA[1566019583984383428]]></out_trade_no>
         * 	<result_code><![CDATA[SUCCESS]]></result_code>
         * 	<return_code><![CDATA[SUCCESS]]></return_code>
         * 	<sign><![CDATA[9879B2CDF0CE2E93EB1A12E1928D49EE]]></sign>
         * 	<time_end><![CDATA[20190817155832]]></time_end>
         * 	<total_fee>1</total_fee>
         * 	<trade_type><![CDATA[JSAPI]]></trade_type>
         * 	<transaction_id><![CDATA[4200000387201908179183467465]]></transaction_id>
         * </xml>
         */
        try {
            Document document= DocumentHelper.parseText(notifyData);
            Node out_trade_no = document.selectSingleNode("//out_trade_no");
            orderId = out_trade_no.getText();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Optional<CompanyOrder> companyOrder = companyOrderRepository.findById(orderId);
        Optional<MemberOrder> memberOrder = memberOrderRepository.findById(orderId);
        Optional<PartInfo> partOrder = partInfoRepository.findById(orderId);
        if(companyOrder.isPresent()){
            //TODO 修改企业订单表
            CompanyOrder companyOrderReally = companyOrder.get();
            companyOrderReally.setActivityStatus(ActivityStatusEnum.NEW.getCode());
        }else if(memberOrder.isPresent()){
            //修改企业会员的表
            //修改订单的状态
            MemberOrder memberOrderReally = memberOrder.get();
            log.info("[PayNotifyCallbackImpl] saveMemberOrder={}", memberOrderReally);
            memberOrderReally.setPayStatus(PayStatusEnum.PAY_FINISH.getCode());
            memberOrderRepository.save(memberOrderReally);
            String orderCompanyId = memberOrder.get().getOrderCompany();
            //找到对应的公司修改为会员
            Optional<CompanyInfo> orderCompanyOpt = companyInfoRepository.findById(orderCompanyId);
            if(orderCompanyOpt.isPresent()){
                CompanyInfo companyInfo = orderCompanyOpt.get();

                //根据订单修改状态
                Integer memberLevel = memberOrderReally.getMemberLevel();
                companyInfo.setCompanyMember(memberLevel);
                if(memberLevel.equals(MemberLevelEnum.ONE_LEVEL.getCode())){
                    companyInfo.setMemberOverdue(System.currentTimeMillis() + (30L * 24L * 3600L * 1000L));
                }else if(memberLevel.equals(MemberLevelEnum.TWO_LEVEL.getCode())){
                    companyInfo.setMemberOverdue(System.currentTimeMillis() + (365L * 24L * 3600L * 1000L));
                }else if(memberLevel.equals(MemberLevelEnum.THREE_LEVEL.getCode())){
                    companyInfo.setMemberOverdue(System.currentTimeMillis() + (100L * 365L * 24L * 3600L * 1000L));
                }
                //判断是哪种会员
                CompanyInfo saveResult = companyInfoRepository.save(companyInfo);
                if(saveResult == null) log.error("saveResult = {}", companyInfo);
            }else{
                throw new BuckMooException(ErrorResultEnum.COMPANY_INFO_NOT_EXIT);
            }
        }else if(partOrder.isPresent()){
            //修改用户发布兼职表
            PartInfo partInfo = partInfoService.modifyPartStatus(partOrder.get().getPartId(), PartTimeStatusEnum.NEW_PART.getCode());
            //抽取5%佣金
            partInfo.setPartMoneyShow(partInfo.getPartMoney().multiply(new BigDecimal(0.05)));
            PartInfo updateResult = partInfoService.addOnePartTime(partInfo);
            log.info("[PayNotifyCallbackImpl] updateResult={}", updateResult);
        }

        //修改统一订单表
        Optional<CollectionOrder> findResult = collectionOrderRepository.findById(orderId);
        if(findResult.isPresent()){
            CollectionOrder collectionOrder = findResult.get();
            collectionOrder.setOrderPayStatus(PayStatusEnum.PAY_FINISH.getCode());
            CollectionOrder saveResult = collectionOrderRepository.save(collectionOrder);
            if(saveResult == null) log.error("[PayNotifyCallbackImpl] collectionOrder={}", collectionOrder);
        }else{
            throw new BuckMooException(ErrorResultEnum.THIS_ORDER_NOT_EXITS);
        }
    }
}