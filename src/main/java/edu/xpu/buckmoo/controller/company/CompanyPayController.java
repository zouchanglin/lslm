package edu.xpu.buckmoo.controller.company;

import com.lly835.bestpay.model.PayResponse;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.MemberOrder;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.service.CompanyPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author tim
 * @version 1.1
 * @className PayController
 * @description
 * @date 2019-08-16 20:26
 */
@Controller
@RequestMapping("/company/pay")
@Slf4j
public class CompanyPayController {
    private final CompanyService companyService;
    private final CompanyPayService companyPayService;

    public CompanyPayController(CompanyService companyService, CompanyPayService payService) {
        this.companyService = companyService;
        this.companyPayService = payService;
    }

    //成为会员
    @GetMapping("/member")
    public String member(@CookieValue("openid") String openid,
                         @RequestParam("returnUrl") String returnUrl,
                         Map<String, Object> map){
        CompanyInfo findResult = companyService.findByOpenid(openid);
        MemberOrder memberOrder = companyService.becomeMemberPay(findResult.getCompanyId());
        PayResponse payResponse = companyPayService.memberPay(memberOrder);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return "pay";
    }

    /**
     * 微信的回支付成功的回调接口
     * @param notifyData 来自微信服务器的字段
     * @return 成功的XML数据
     */
    @PostMapping("notify")
    public String payNotify(@RequestBody String notifyData){
        companyPayService.payNotify(notifyData);
        //处理结果返回给微信
        return "pay/success";
    }
}