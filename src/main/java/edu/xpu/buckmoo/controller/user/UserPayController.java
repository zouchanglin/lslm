package edu.xpu.buckmoo.controller.user;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.ErrorResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.service.PartInfoService;
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

    public UserPayController(PartInfoService partService, UserPayService payService) {
        this.partService = partService;
        this.payService = payService;
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
}