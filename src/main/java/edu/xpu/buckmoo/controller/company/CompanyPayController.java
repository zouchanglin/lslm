package edu.xpu.buckmoo.controller.company;

import com.lly835.bestpay.model.PayResponse;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.enums.*;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.service.CompanyPayService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    private final CompanyInfoRepository companyInfoRepository;

    public CompanyPayController(CompanyService companyService, CompanyPayService payService, CompanyInfoRepository companyInfoRepository) {
        this.companyService = companyService;
        this.companyPayService = payService;
        this.companyInfoRepository = companyInfoRepository;
    }

    //成为会员
    @GetMapping("/member")
    public String member(@CookieValue("openid") String openid,
                         @RequestParam("returnUrl") String returnUrl,
                         Map<String, Object> map){
        CompanyInfo findResult = companyService.findByOpenid(openid);

        if(findResult == null){
            throw new BuckMooException(ErrorResultEnum.COMPANY_INFO_NOT_EXIT);
        }else if(!findResult.getCompanyStatus().equals(CompanyStatusEnum.PASS.getCode())){
            throw new BuckMooException(ErrorResultEnum.COMPANY_STATUS_ERROR);
        }

        MemberOrder memberOrder = companyService.becomeMemberPay(findResult.getCompanyId());
        PayResponse payResponse = companyPayService.memberPay(memberOrder);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return "pay";
    }

    @GetMapping("/activity")
    public String activity(@CookieValue("openid") String openid,
                           String activityId,
                           @RequestParam("returnUrl") String returnUrl,
                           Map<String, Object> map){
        CompanyInfo companyInfoFind = companyInfoRepository.findOneByOpenid(openid);
        if(companyInfoFind == null){
            log.error("[CompanyPayServiceImpl] 非企业用户，不可发布活动");
            return JsonUtil.toJson(ResultVOUtil.error(1, "非企业用户，不可发布活动"));
        }else{
            CompanyInfo companyInfo = companyInfoFind;
            if(!companyInfo.getCompanyStatus().equals(CompanyStatusEnum.PASS.getCode())){
                log.error("[CompanyPayServiceImpl] 该企业尚未通过校验，不可发布活动");
                return JsonUtil.toJson(ResultVOUtil.error(2, "该企业尚未通过校验，不可发布活动"));
            }else{
                if(companyInfo.getCompanyMember().equals(MemberLevelEnum.COMMON.getCode())){
                    PayResponse payResponse = companyPayService.activityPay(companyInfo, activityId);
                    map.put("payResponse", payResponse);
                    map.put("returnUrl", returnUrl);
                    return "pay";
                }
            }
        }
        throw new RuntimeException("参数不合法");
    }
}