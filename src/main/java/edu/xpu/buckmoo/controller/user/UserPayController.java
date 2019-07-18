package edu.xpu.buckmoo.controller.user;

import com.lly835.bestpay.model.PayResponse;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private PartInfoService partService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public String create(@RequestParam("partId") String partId,
                       @RequestParam("returnUrl") String returnUrl,
                       Map<String, Object> map){
        //1、查询兼职信息
        PartInfo oneById = partService.findOneById(partId);
        if(oneById == null) {
            log.info("partId={}", partId);
            throw new BuckMooException(ResultEnum.PART_NOT_EXIT);
        }

        //2、发起支付
        PayResponse payResponse = payService.partPay(oneById);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);
        return "pay";
    }

}
