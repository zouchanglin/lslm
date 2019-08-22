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

import java.util.Map;

/**
 * @author tim
 * @version 1.2
 * @className PayController
 * @description 企业支付的控制器
 * @date 2019-08-19 20:26
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

    /**
     * 成为会员需要支付的接口
     * @param openid cookie存储的openid
     * @param returnUrl 支付成功后返回的地址
     * @param map 填充参数
     * @return 微信H5支付页面
     */
    @GetMapping("/member")
    public String member(@CookieValue("openid") String openid,
                         @RequestParam("returnUrl") String returnUrl,
                         @RequestParam(value = "memberLevel", defaultValue = "1") Integer memberLevel,
                         Map<String, Object> map){

        if(openid == null){
            map.put("error_code", 3);
            map.put("error_msg", "请先登录在使用");
            return "error";
        }

        //先根据openid找企业信息
        CompanyInfo findResult = companyService.findByOpenid(openid);

        //原来的抛出异常处理变为返回值
        if(findResult == null){
            //throw new BuckMooException(ErrorResultEnum.COMPANY_INFO_NOT_EXIT);
            //return JsonUtil.toJson(ResultVOUtil.error(1, "请先注册企业"));
            map.put("error_code", 1);
            map.put("error_msg", "请先注册企业");
            return "error";
        }else if(!findResult.getCompanyStatus().equals(CompanyStatusEnum.PASS.getCode())){
            //throw new BuckMooException(ErrorResultEnum.COMPANY_STATUS_ERROR);
            //return JsonUtil.toJson(ResultVOUtil.error(2, "请先等待公司信息审核通过才能成为会员"));
            map.put("error_code", 2);
            map.put("error_msg", "请先等待公司信息审核通过才能成为会员");
            return "error";
        }

        //生成企业升级为会员的订单
        try{
            MemberOrder memberOrder = companyService.becomeMemberPay(findResult.getCompanyId(), memberLevel);
            //生产预支付订单
            PayResponse payResponse = companyPayService.memberPay(memberOrder);

            //填充支付相关参数
            map.put("payResponse", payResponse);
            map.put("returnUrl", returnUrl);
            return "pay";
        }catch (BuckMooException e){
            e.printStackTrace();
            map.put("error_code", -1);
            map.put("error_msg", "公司已经是会员");
            return "error";
        }
    }

    /**
     * 支付发布活动需要的费用
     * @param openid cookie存储的openid
     * @param activityId 活动的Id
     * @param returnUrl 支付成功后返回的地址
     * @return 微信H5支付页面
     */
    @GetMapping("/activity")
    public String activity(@CookieValue("openid") String openid,
                           String activityId,
                           @RequestParam("returnUrl") String returnUrl,
                           Map<String, Object> map){
        CompanyInfo companyInfoFind = companyInfoRepository.findOneByOpenid(openid);
        //先判断企业信息是否存在
        if(companyInfoFind == null){
            //不存在打印log
            log.error("[CompanyPayServiceImpl] 非企业用户，不可发布活动");
            return JsonUtil.toJson(ResultVOUtil.error(1, "非企业用户，不可发布活动"));
        }else{
            //判断企业是否已经审核通过
            if(!companyInfoFind.getCompanyStatus().equals(CompanyStatusEnum.PASS.getCode())){
                log.error("[CompanyPayServiceImpl] 该企业尚未通过校验，不可发布活动");
                return JsonUtil.toJson(ResultVOUtil.error(2, "该企业尚未通过校验，不可发布活动"));
            }else{
                //判断企业是否是会员
                //if(companyInfoFind.getCompanyMember().equals(MemberLevelEnum.COMMON.getCode())){
                //不是入住会员则需要支付
                PayResponse payResponse = companyPayService.activityPay(companyInfoFind, activityId);
                map.put("payResponse", payResponse);
                map.put("returnUrl", returnUrl);
                return "pay";
                //}
            }
        }
    }
}