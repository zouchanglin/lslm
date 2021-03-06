package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.convert.UserInfo2VO;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tim
 * @version 1.2
 * @className UserLoginController
 * @description 用户信息控制器
 * @date 2019-08-19 21:36
 */
@RestController
@Slf4j
@RequestMapping("/user/info")
public class UserInfoController {
    private static Map<String, String> verifyKeyMap = new HashMap<>();

    private final UserInfoService userInfoService;
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/show")
    public String getUserInfo(@CookieValue(value = "openid", required = false)
                                          String openid){
        if(openid == null){
            return JsonUtil.toJson(ResultVOUtil.error(1, "请授权登录后使用"));
        }
        UserInfo findRet = userInfoService.findById(openid);
        if(findRet != null){
            return JsonUtil.toJson(ResultVOUtil.success(UserInfo2VO.userInfoToUserInfoVO(findRet)));
        }
        return JsonUtil.toJson(ResultVOUtil.error(2, "当前人数过多，请客观稍后再来~"));
    }

    @GetMapping("/verifykey")
    public String verifykey(@CookieValue(value = "openid", required = false)
                                          String openid, String phone){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(1, "请授权登录后使用"));
        if(phone == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请填写手机号"));
        if(!VerifyUtil.verifyPhoneNumber(phone)){
            return JsonUtil.toJson(ResultVOUtil.error(1, "手机号格式不正确"));
        }
        //生成验证码
        String verifyKey = KeyUtil.genVerifyKey();
        SendMessageUtil.sendMsg(phone, verifyKey);
        verifyKeyMap.put(phone, verifyKey);
        return JsonUtil.toJson(ResultVOUtil.success());
    }

    @GetMapping("/bindphone")
    public String bindPhone(@CookieValue(value = "openid", required = false)
                                    String openid, String phone, String verifykey){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(1, "请授权登录后使用"));
        String getVerifyKey = verifyKeyMap.get(phone);
        if(getVerifyKey != null && getVerifyKey.equals(verifykey)){
            UserInfo userInfo = userInfoService.findById(openid);
            userInfo.setUserPhone(phone);
            userInfoService.saveUser(userInfo);
            return JsonUtil.toJson(ResultVOUtil.success());
        }
        return JsonUtil.toJson(ResultVOUtil.error(1, "验证码错误"));
    }


}