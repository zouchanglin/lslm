package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.enums.WeChatPayNotifyEnum;
import edu.xpu.buckmoo.service.CompanyPayService;
import edu.xpu.buckmoo.service.PayNotifyCallback;
import edu.xpu.buckmoo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private  PayNotifyCallback payNotifyCallback;

    @Override
    public PayResponse memberPay(MemberOrder memberOrder) {
        PayRequest payRequest = new PayRequest();
        //payRequest.setOpenid(memberOrder.getOpenid());
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
        payNotifyCallback.payNotify(notifyData, WeChatPayNotifyEnum.COMPANY_PAY.getCode());
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