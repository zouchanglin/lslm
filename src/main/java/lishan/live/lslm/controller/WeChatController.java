package lishan.live.lslm.controller;

import lishan.live.lslm.enums.ResultEnum;
import lishan.live.lslm.exception.WeChatException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 用于微信API获取的控制层
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    private final WxMpService wxMpService;

    @Autowired
    public WeChatController(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1 配置

        //2 调用方法
        String path = "http://lslm.live/sell/wechat/userInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(
                    path, WxConsts.OAUTH2_SCOPE_USER_INFO,
                    URLEncoder.encode(returnUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("【微信网页授权获取code】result={}", redirectUrl);
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(
            @RequestParam("code") String code,
            @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken accessToken;
        try {
            accessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new WeChatException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = accessToken.getOpenId();
        log.info("openid={}", openId);
        return "redirect:"+returnUrl+"?openid="+openId;
    }
}