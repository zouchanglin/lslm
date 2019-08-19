package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.dataobject.order.CompanyOrder;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.enums.*;
import edu.xpu.buckmoo.repository.CollectionOrderRepository;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.repository.config.SystemConfigRepository;
import edu.xpu.buckmoo.repository.order.CompanyOrderRepository;
import edu.xpu.buckmoo.service.CompanyPayService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tim
 * @version 1.1
 * @className PayServiceImpl
 * @description
 * @date 2019-06-22 00:45
 */
@Service
@Slf4j
public class CompanyPayServiceImpl implements CompanyPayService{
    private final SystemConfigRepository systemConfigRepository;
    private final BestPayServiceImpl bestPayService;
    private final CompanyInfoRepository companyInfoRepository;
    private final CollectionOrderRepository collectionOrderRepository;
    private final CompanyOrderRepository companyOrderRepository;

    public CompanyPayServiceImpl(BestPayServiceImpl bestPayService,
                                 CompanyInfoRepository companyInfoRepository,
                                 CollectionOrderRepository collectionOrderRepository,
                                 SystemConfigRepository systemConfigRepository,
                                 CompanyOrderRepository companyOrderRepository) {
        this.bestPayService = bestPayService;
        this.companyInfoRepository = companyInfoRepository;
        this.collectionOrderRepository = collectionOrderRepository;
        this.systemConfigRepository = systemConfigRepository;
        this.companyOrderRepository = companyOrderRepository;
    }

    @Override
    public PayResponse memberPay(MemberOrder memberOrder) {
        PayRequest payRequest = new PayRequest();

        String orderCompany = memberOrder.getOrderCompany();
        Optional<CompanyInfo> findResult = companyInfoRepository.findById(orderCompany);
        String openid;
        if(findResult.isPresent()){
            openid = findResult.get().getOpenid();
            payRequest.setOpenid(openid);
            payRequest.setOrderId(memberOrder.getOrderId());
            payRequest.setOrderAmount(memberOrder.getOrderMoney().doubleValue());
            payRequest.setOrderName(memberOrder.getOrderCompany() + "升级为会员");
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

            //同时生成统一订单
            CollectionOrder collectionOrder = new CollectionOrder();
            collectionOrder.setOrderId(memberOrder.getOrderId());
            collectionOrder.setOrderId(openid);
            collectionOrder.setOrderMoney(memberOrder.getOrderMoney());
            collectionOrder.setOrderPayStatus(PayStatusEnum.NOT_PAY.getCode());
            collectionOrder.setOrderType(CollectionOrderTypeEnum.COMPANY_MEMBER_PAY.getCode());
            collectionOrder.setOrderName(findResult.get().getCompanyName() + "升级为会员");

            CollectionOrder saveResult = collectionOrderRepository.save(collectionOrder);
            log.info("[CompanyPayServiceImpl] saveResult={}", saveResult);
        }

        log.info("[CompanyPayServiceImpl] payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[CompanyPayServiceImpl] payResponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse activityPay(CompanyInfo companyInfo, String activityId) {
        //非会员发布活动
        //生成统一订单
        CollectionOrder collectionOrder = new CollectionOrder();
        Optional<SystemConfig> oneActivityMoney = systemConfigRepository.findById("one_activity_money");
        if(oneActivityMoney.isPresent()){
            String key = KeyUtil.genUniqueKey();
            collectionOrder.setOrderId(key);
            collectionOrder.setOrderMoney(oneActivityMoney.get().getParamsValue());
            collectionOrder.setOrderType(CollectionOrderTypeEnum.COMPANY_ACTIVITY_PAY.getCode());
            collectionOrder.setOrderPayStatus(PayStatusEnum.NOT_PAY.getCode());
            collectionOrder.setOrderName(companyInfo.getCompanyName() + "(非会员)发布活动");
            collectionOrder.setOrderOpenid(companyInfo.getOpenid());

            CollectionOrder collectSaveResult = collectionOrderRepository.save(collectionOrder);
            log.info("[CompanyPayServiceImpl] collectSaveResult={}", collectSaveResult);

            //生成发布活动支付订单
            CompanyOrder companyOrder = new CompanyOrder();
            companyOrder.setOrderId(key);
            companyOrder.setActivityStatus(ActivityStatusEnum.NEW.getCode());
            companyOrder.setOrderMoney(collectionOrder.getOrderMoney());
            companyOrder.setCreateTime(System.currentTimeMillis());
            companyOrder.setOrderActivity(activityId);
            companyOrder.setUpdateTime(System.currentTimeMillis());

            CompanyOrder saveCompanyOrderResult = companyOrderRepository.save(companyOrder);
            log.info("[CompanyPayServiceImpl] saveCompanyOrderResult={}", saveCompanyOrderResult);


            PayRequest payRequest = new PayRequest();
            payRequest.setOpenid(companyInfo.getOpenid());
            payRequest.setOrderAmount(companyOrder.getOrderMoney().doubleValue());
            payRequest.setOrderId(companyOrder.getOrderId());
            payRequest.setOrderName(collectionOrder.getOrderName());
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

            PayResponse payResponse = bestPayService.pay(payRequest);
            log.info("[CompanyPayServiceImpl] payResponse={}", payResponse);
            return payResponse;
        }else{
            log.error("[CompanyPayServiceImpl] 系统参数丢失:one_activity_money");
            throw new RuntimeException("系统参数丢失:one_activity_money");
        }
    }
}