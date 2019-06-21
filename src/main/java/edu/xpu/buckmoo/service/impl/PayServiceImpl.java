package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.service.PayService;
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
public class PayServiceImpl implements PayService{
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Override
    public void partPay(PartInfo partInfo) {
        PayRequest payRequest = new PayRequest();
        bestPayService.pay(payRequest);
        //TODO 支付（8-2微信发起后端支付3:42）
    }
}
