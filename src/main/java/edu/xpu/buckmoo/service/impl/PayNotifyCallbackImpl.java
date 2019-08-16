package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.MemberOrder;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.enums.*;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.repository.MemberOrderRepository;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.PayNotifyCallback;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class PayNotifyCallbackImpl implements PayNotifyCallback {
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private PartInfoService partInfoService;

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    private MemberOrderRepository memberOrderRepository;

    @Override
    public void payNotify(String notifyData, Integer mode) {
        //用户支付的兼职款项
        if(WeChatPayNotifyEnum.USER_PAY.getCode().equals(mode)){
            PayResponse payResponse = bestPayService.asyncNotify(notifyData);
            log.info("[微信支付异步通知] payResponse={}", JsonUtil.toJson(payResponse));

            //先查找兼职信息
            PartInfo findRet = partInfoService.findOneById(payResponse.getOrderId());
            if(findRet == null) throw new BuckMooException(ResultEnum.PART_NOT_EXIT);

            //判断兼职支付金额
            if(!MathUtil.equals(payResponse.getOrderAmount(), findRet.getPartMoney().doubleValue())){
                log.error("[微信支付异步通知] payResponse.getOrderAmount() = {}", payResponse);
                throw new BuckMooException(ResultEnum.WECHAT_PAY_ERROR);
            }

            PartInfo partInfo = partInfoService.modifyPartStatus(payResponse.getOrderId(), PartTimeStatusEnum.NEW_PART.getCode());

            //抽取5%佣金
            partInfo.setPartMoneyShow(partInfo.getPartMoney().multiply(new BigDecimal(0.05)));
            partInfoService.addOnePartTime(partInfo);
        }else if(WeChatPayNotifyEnum.COMPANY_PAY.getCode().equals(mode)){
            PayResponse payResponse = bestPayService.asyncNotify(notifyData);
            log.info("[微信支付异步通知] payResponse={}", JsonUtil.toJson(payResponse));

            String orderId = payResponse.getOrderId();
            Optional<MemberOrder> findResult = memberOrderRepository.findById(orderId);
            if(findResult.isPresent()){
                MemberOrder memberOrder = findResult.get();
                memberOrder.setPayStatus(CompanyOrderEnum.PAY_FINISH.getCode());
                //修改订单的状态
                MemberOrder saveMemberOrder = memberOrderRepository.save(memberOrder);
                log.info("saveMemberOrder={}", saveMemberOrder);

                String orderCompanyId = memberOrder.getOrderCompany();
                //找到公司做VIP修改
                Optional<CompanyInfo> orderCompanyOpt = companyInfoRepository.findById(orderCompanyId);
                if(orderCompanyOpt.isPresent()){
                    CompanyInfo companyInfo = orderCompanyOpt.get();
                    companyInfo.setCompanyMember(MemberLevelEnum.ONE_LEVEL.getCode());
                    CompanyInfo saveResult = companyInfoRepository.save(companyInfo);
                    log.info("[修改公司会员等级] saveResult={}", saveResult);
                    assert saveResult != null;
                }else{
                    throw new BuckMooException(ResultEnum.COMPANY_INFO_NOT_EXIT);
                }
            }else{
                throw new BuckMooException(ResultEnum.COMPANY_INFO_NOT_EXIT);
            }
        }else{

        }
    }
}
