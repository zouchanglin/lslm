package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.MemberOrder;
import edu.xpu.buckmoo.enums.CompanyOrderEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.repository.MemberOrderRepository;
import edu.xpu.buckmoo.service.CompanyPayService;
import edu.xpu.buckmoo.utils.JsonUtil;
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
    private final BestPayServiceImpl bestPayService;

    private final CompanyInfoRepository companyInfoRepository;

    private final MemberOrderRepository memberOrderRepository;

    public CompanyPayServiceImpl(BestPayServiceImpl bestPayService, CompanyInfoRepository companyInfoRepository, MemberOrderRepository memberOrderRepository) {
        this.bestPayService = bestPayService;
        this.companyInfoRepository = companyInfoRepository;
        this.memberOrderRepository = memberOrderRepository;
    }

    @Override
    public PayResponse memberPay(MemberOrder memberOrder) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(memberOrder.getOpenid());
        payRequest.setOrderId(memberOrder.getOrderId());
        payRequest.setOrderAmount(memberOrder.getOrderMoney().doubleValue());
        payRequest.setOrderName(memberOrder.getOrderCompany() + "升级为会员");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("payResponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public void payNotify(String notifyData) {
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
    }

    @Override
    public RefundResponse memberRefund(MemberOrder memberOrder) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(memberOrder.getOrderId());
        refundRequest.setOrderAmount(memberOrder.getOrderMoney().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("[微信退款] refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}