package edu.xpu.buckmoo.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;

/**
 * @author tim
 * @version 1.1
 * @className PayService
 * @description
 * @date 2019-08-16 00:02
 */
public interface CompanyPayService {
    /**
     * 支付策划能够为会员的费用
     * @param memberOrder 订单Id
     * @return 支付结果
     */
    PayResponse memberPay(MemberOrder memberOrder);

    /**
     * 成为会员支付退款
     * @param memberOrder 订单详情
     * @return 退款订单详情
     */
    RefundResponse memberRefund(MemberOrder memberOrder);
}