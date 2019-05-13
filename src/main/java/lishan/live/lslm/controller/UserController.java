package lishan.live.lslm.controller;

import lishan.live.lslm.entity.UserInfo;
import lishan.live.lslm.enums.ResultEnum;
import lishan.live.lslm.exception.UserException;
import lishan.live.lslm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description
 * @Author tim
 * @Date 2019-04-29 16:12
 * @Version 1.0
 */

@Controller
@Slf4j
@RequestMapping(value = "/admin/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/show")
    public String show(Map<String, Object> map,
                       @RequestParam(value = "userName", required = false) String userName){
        List<UserInfo> userInfoList;
        if(userName == null){
            userInfoList = userService.findAll();
        }else{
            userInfoList = userService.findAllByNameLike(userName);
        }
        log.info("[AdminController]: userInfoList.size() = {}", userInfoList.size());
        map.put("userInfoList", userInfoList);
        return "admin-user";
    }


    @GetMapping("/delete")
    public String deleteUserInfo(@RequestParam("userOpenid")String userOpenid){
        userService.deleteUserInfo(userOpenid);
        return "success";
    }


    @GetMapping("/update")
    public String updateUserInfo(@RequestParam(value = "userOpenid")String userOpenid,
                                    Map<String, Object> map){
        UserInfo userInfo = userService.findUserInfoById(userOpenid);
        if(!userOpenid.equals(userInfo.getUserOpenid()))
            //如果用户信息不存在就抛出异常，因为这个是修改的接口
            throw new UserException(ResultEnum.ENTITY_NOT_EXIST);

        map.put("userInfo", userInfo);
        return "user/user-update";
    }


    @RequestMapping("/update-info")
    public String updateCompanyInfo(@ModelAttribute UserInfo userInfo){
        if(userInfo == null) return "error";
        log.info("【页面传回UserInfo】{}", userInfo);
        //收到修改后的数据
        UserInfo saveRet = userService.saveUserInfo(userInfo);
        return saveRet != null ? "success":"error";
    }
}
