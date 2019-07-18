package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.service.PayService;
import edu.xpu.buckmoo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private BestPayServiceImpl bestPayService;

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
}
