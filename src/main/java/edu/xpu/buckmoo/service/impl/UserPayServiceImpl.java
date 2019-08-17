package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.service.UserPayService;
import edu.xpu.buckmoo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author tim
 * @version 1.2
 * @className PayServiceImpl
 * @description
 * @date 2019-08-17 00:45
 */
@Service
@Slf4j
public class UserPayServiceImpl implements UserPayService{
    private final BestPayServiceImpl bestPayService;

    public UserPayServiceImpl(BestPayServiceImpl bestPayService) {
        this.bestPayService = bestPayService;
    }

    @Override
    public PayResponse partPay(PartInfo partInfo) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(partInfo.getPartCreator());
        payRequest.setOrderId(partInfo.getPartId());
        payRequest.setOrderAmount(partInfo.getPartMoney().doubleValue());
        payRequest.setOrderName(partInfo.getPartName());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("payResponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public RefundResponse refund(PartInfo partInfo) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(partInfo.getPartId());
        refundRequest.setOrderAmount(partInfo.getPartMoney().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("[微信退款] refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
