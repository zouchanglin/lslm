package edu.xpu.buckmoo.controller;

import com.alibaba.fastjson.JSONObject;
import edu.xpu.buckmoo.config.ProjectUrlConfig;
import edu.xpu.buckmoo.convert.WxMpUser2UserInfo;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author tim
 * @version 1.0
 * @className WeChatController
 * @date 2019-06-21 15:31
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1、配置
        //2、调用方法
        String url = projectUrlConfig.getWechatMpAuthorize() + "/buckmoo/wechat/userInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("【微信网页授权】{}", e.toString());
            e.printStackTrace();
        }
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl,
                           HttpServletResponse response,
                           @CookieValue(value = "userid", required = false) String userid){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            UserInfo userInfo = WxMpUser2UserInfo.WechatMpUser2UserInfo(wxMpUser);
            UserInfo saveOrUpdate = userInfoService.saveUser(userInfo);
            log.info("saveOrUpdate = {}", saveOrUpdate);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e.toString());
            throw new BuckMooException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();

        if(userid == null){
            Cookie cookie = new Cookie("userid", openId);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        }
        return "redirect:" + returnUrl + "?openid=" + openId;
    }


    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl){
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/buckmoo/wechat/qrUserInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxOpenService.buildQrConnectUrl(url,  WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
            log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e.toString());
            throw new BuckMooException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        return "redirect:" + returnUrl + "?openid=" + wxMpOAuth2AccessToken.getOpenId();
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
