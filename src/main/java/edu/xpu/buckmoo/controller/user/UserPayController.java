package edu.xpu.buckmoo.controller.user;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.ErrorResultEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.service.MemberService;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.service.UserPayService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author tim
 * @version 1.0
 * @className UserPayController
 * @description 用户支付控制器
 * @date 2019-06-20 21:35
 */
@Controller
@Slf4j
@RequestMapping("/user/pay")
public class UserPayController {
    private final PartInfoService partService;
    private final UserPayService payService;
    private final UserInfoService userInfoService;
    private final MemberService memberService;

    public UserPayController(PartInfoService partService,
                             UserPayService payService,
                             UserInfoService userInfoService,
                             MemberService memberService) {
        this.partService = partService;
        this.payService = payService;
        this.userInfoService = userInfoService;
        this.memberService = memberService;
    }

    /**
     * 支付已经发布的兼职信息
     * @param partId 兼职信息主键
     * @param returnUrl 支付后跳转地址
     * @return H5支付页面
     */
    @GetMapping("/create")
    public String create(@RequestParam("partId") String partId,
                       @RequestParam("returnUrl") String returnUrl,
                       Map<String, Object> map){
        //1、查询兼职信息
        PartInfo oneById = partService.findOneById(partId);
        if(oneById == null) {
            log.info("partId={}", partId);
            throw new BuckMooException(ErrorResultEnum.PART_NOT_EXIT);
        }

        //2、发起支付
        PayResponse payResponse = payService.partPay(oneById);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return "pay";
    }

    /**
     * 微信退款
     * @param partId 要退款的兼职信息单号
     * @return 退款申请提交成功
     */
    @GetMapping("/refund")
    public String refund(@RequestParam("partId") String partId){
        PartInfo partInfo = partService.findOneById(partId);
        if(partInfo == null) throw new BuckMooException(ErrorResultEnum.PART_NOT_EXIT);
        RefundResponse response = payService.refund(partInfo);
        log.info("[微信退款] response={}", response);
        return JsonUtil.toJson(ResultVOUtil.success());
    }

    @GetMapping("/tobe_member")
    public String tobe_member(@CookieValue(value = "openid", required = false) String openid,
                              @RequestParam("memberLevel") Integer memberLevel,
                              @RequestParam("returnUrl") String returnUrl,
                              Map<String, Object> map){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(1, "请授权登录后使用"));
        UserInfo userInfo = userInfoService.findById(openid);
        if(!MemberLevelEnum.COMMON.getCode().equals(userInfo.getUserMember())){
            //TODO 用户会员升级或者续费
            System.out.println("TODO 用户会员升级或者续费");
        }else{
            CollectionOrder collectionOrder = memberService.addNewUserMember(memberLevel, openid);
            PayResponse payResponse = payService.memberPay(collectionOrder);
            map.put("payResponse", payResponse);
            map.put("returnUrl", returnUrl);
            return "pay";
        }
        return null;
    }
}