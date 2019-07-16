package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tim
 * @version 1.0
 * @className UserLoginController
 * @description
 * @date 2019-06-20 21:36
 */
@RestController
@Slf4j
@RequestMapping("/user/info")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/show")
    public String getUserInfo(@RequestParam("openid") String openid){
        UserInfo findRet = userInfoService.findById(openid);
        if(findRet != null)
            return JsonUtil.toJson(ResultVOUtil.success(findRet));
        return JsonUtil.toJson(ResultVOUtil.error(1, "用户信息为空"));
    }


}
