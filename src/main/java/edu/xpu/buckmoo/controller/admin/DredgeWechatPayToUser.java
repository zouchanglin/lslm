package edu.xpu.buckmoo.controller.admin;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 一个用于开通企业到用户的支付功能
 */
@Controller
@RequestMapping("/admin/dredge")
@Slf4j
public class DredgeWechatPayToUser {
    @Autowired
    private BestPayServiceImpl bestPayService;

    @GetMapping("/pay")
    public String adminPay(@CookieValue(value = "openid", required = false) String openid,
                           String returnUrl,
                           Map<String, Object> map){
        if(openid == null) {
            log.error("未登录，无法支付");
            return null;
        }
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(openid);
        payRequest.setOrderId(KeyUtil.genUniqueKey());
        payRequest.setOrderAmount(0.01);
        payRequest.setOrderName("开通企业支付到零钱");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        PayResponse payResponse = bestPayService.pay(payRequest);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return "pay";
    }
}