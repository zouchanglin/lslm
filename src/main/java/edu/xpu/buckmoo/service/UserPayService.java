package edu.xpu.buckmoo.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.order.PartInfo;

/**
 * @author tim
 * @version 1.0
 * @className PayService
 * @description
 * @date 2019-06-22 00:44
 */
public interface UserPayService {
    PayResponse partPay(PartInfo partInfo);

    RefundResponse refund(PartInfo partInfo);

    PayResponse memberPay(CollectionOrder collectionOrder);
}