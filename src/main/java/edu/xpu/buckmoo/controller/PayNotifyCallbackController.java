package edu.xpu.buckmoo.controller;

import edu.xpu.buckmoo.service.PayNotifyCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/allpay")
@Slf4j
public class PayNotifyCallbackController {

    private final PayNotifyCallback payNotifyCallback;

    public PayNotifyCallbackController(PayNotifyCallback payNotifyCallback) {
        this.payNotifyCallback = payNotifyCallback;
    }

    @PostMapping("notify")
    public String payNotify(@RequestBody String notifyData){
        log.info("[PayNotifyCallbackController] notifyData={}", notifyData);

        //拿到了OrderId、分别处理
        payNotifyCallback.payNotify(notifyData);
        //处理结果返回给微信
        return "pay/success";
    }
}
