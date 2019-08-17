package edu.xpu.buckmoo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/allpay")
@Slf4j
public class PayNotifyCallbackController {

    @PostMapping("notify")
    public String payNotify(@RequestBody String notifyData){
        log.info("[PayNotifyCallbackController] notifyData={}", notifyData);
        //处理结果返回给微信
        return "pay/success";
    }
}
