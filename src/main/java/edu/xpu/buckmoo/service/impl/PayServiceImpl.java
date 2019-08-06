package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.PayService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author tim
 * @version 1.0
 * @className PayServiceImpl
 * @description
 * @date 2019-06-22 00:45
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService{
    private final BestPayServiceImpl bestPayService;
    private final PartInfoService partInfoService;

    public PayServiceImpl(BestPayServiceImpl bestPayService, PartInfoService partInfoService) {
        this.bestPayService = bestPayService;
        this.partInfoService = partInfoService;
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
    public void payNotify(String notifyData) {
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
