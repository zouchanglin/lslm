package edu.xpu.buckmoo.controller;

import com.alibaba.fastjson.JSONObject;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

/**
 * @author tim
 * @version 1.0
 * @className WeChatController
 * @description
 * @date 2019-06-21 15:31
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1、配置
        //2、调用方法
        String url = "http://tim.natapp1.cc/buckmoo/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new BuckMooException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String accessToken = wxMpOAuth2AccessToken.getAccessToken();
        log.info("openId={}", openId);
        log.info("accessToken={}", accessToken);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }


    /**
     * 原生的方式获取access_token
     * @param code 微信传回的
     */
    @GetMapping("/auth")
    public void auth_demo(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb39b42f5fd93c167&secret=3d63c96f84e4587baa5e4a6f85d1a515&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        log.info("response={}", forObject);

        String openId = JSONObject.parseObject(forObject).getString("openid");
        String accessToken = JSONObject.parseObject(forObject).getString("access_token");
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
        RestTemplate restTemplateUserInfo = new RestTemplate();
        String userInfo = restTemplateUserInfo.getForObject(userInfoUrl, String.class);
        log.info("userInfo={}", userInfo);
    }
}
