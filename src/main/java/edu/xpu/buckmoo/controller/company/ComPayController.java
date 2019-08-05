package edu.xpu.buckmoo.controller.company;

import com.lly835.bestpay.model.PayResponse;
import edu.xpu.buckmoo.dataobject.MemberOrder;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tim
 * @version 1.0
 * @className PayController
 * @description
 * @date 2019-06-20 19:26
 */
@RestController
@RequestMapping("/company/pay")
@Slf4j
public class ComPayController {
    @Autowired
    private CompanyService companyService;
    //成为会员
    @GetMapping("/member")
    public String member(String companyId,
                         @RequestParam("returnUrl") String returnUrl,
                         Map<String, Object> map){
        MemberOrder memberOrder = companyService.becomeMemberPay(companyId);

        PayResponse payResponse = companyService.memberPay(memberOrder);
        return "";
    }

    //发布活动需要支付费用
}
