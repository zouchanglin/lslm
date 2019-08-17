package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.enums.CollectionOrderTypeEnum;
import edu.xpu.buckmoo.enums.PayStatusEnum;
import edu.xpu.buckmoo.repository.CollectionOrderRepository;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
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
    private final CollectionOrderRepository collectionOrderRepository;

    public CompanyPayServiceImpl(BestPayServiceImpl bestPayService, CompanyInfoRepository companyInfoRepository, CollectionOrderRepository collectionOrderRepository) {
        this.bestPayService = bestPayService;
        this.companyInfoRepository = companyInfoRepository;
        this.collectionOrderRepository = collectionOrderRepository;
    }

    @Override
    public PayResponse memberPay(MemberOrder memberOrder) {
        PayRequest payRequest = new PayRequest();

        String orderCompany = memberOrder.getOrderCompany();
        Optional<CompanyInfo> findResult = companyInfoRepository.findById(orderCompany);
        String openid;
        if(findResult.isPresent()){
            openid = findResult.get().getOpenid();
            payRequest.setOpenid(openid);
            payRequest.setOrderId(memberOrder.getOrderId());
            payRequest.setOrderAmount(memberOrder.getOrderMoney().doubleValue());
            payRequest.setOrderName(memberOrder.getOrderCompany() + "升级为会员");
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

            //同时生成统一订单
            CollectionOrder collectionOrder = new CollectionOrder();
            collectionOrder.setOrderId(memberOrder.getOrderId());
            collectionOrder.setOrderId(openid);
            collectionOrder.setOrderMoney(memberOrder.getOrderMoney());
            collectionOrder.setOrderPayStatus(PayStatusEnum.NOT_PAY.getCode());
            collectionOrder.setOrderType(CollectionOrderTypeEnum.COMPANY_MEMBER_PAY.getCode());
            collectionOrder.setOrderName(findResult.get().getCompanyName() + "升级为会员");

            CollectionOrder saveResult = collectionOrderRepository.save(collectionOrder);
            log.info("[CompanyPayServiceImpl] saveResult={}", saveResult);
        }

        log.info("[CompanyPayServiceImpl] payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[CompanyPayServiceImpl] payResponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public RefundResponse memberRefund(MemberOrder memberOrder) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(memberOrder.getOrderId());
        refundRequest.setOrderAmount(memberOrder.getOrderMoney().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("[CompanyPayServiceImpl] 微信退款 refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[CompanyPayServiceImpl] 微信退款 refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}