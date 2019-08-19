package edu.xpu.buckmoo.service;

import com.lly835.bestpay.model.PayResponse;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;

/**
 * @author tim
 * @version 1.2
 * @className PayService
 * @description
 * @date 2019-08-19 00:02
 */
public interface CompanyPayService {
    /**
     * 支付策划能够为会员的费用
     * @param memberOrder 订单Id
     * @return 待支付订单对象
     */
    PayResponse memberPay(MemberOrder memberOrder);

    /**
     * 非会员企业发布活动支付费用
     * @param companyInfo 非会员企业信息
     * @param activityId 活动Id
     * @return 待支付订单对象
     */
    PayResponse activityPay(CompanyInfo companyInfo, String activityId);
}