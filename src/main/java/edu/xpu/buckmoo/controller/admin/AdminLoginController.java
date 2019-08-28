package edu.xpu.buckmoo.controller.admin;

import com.lly835.bestpay.rest.type.Get;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tim
 * @version 1.0
 * @className LoginController
 * @description
 * @date 2019-06-20 21:28
 */
@RestController
@RequestMapping("/admin/login")
@Slf4j
public class AdminLoginController {
    private static String my_code = "";

    @PostMapping("/verify")
    public String adminLogin(@RequestParam("code") String code,
                             @CookieValue(value = "error_count", required = false) String error_count,
                             HttpSession httpSession,
                             HttpServletResponse response){
        if(my_code.equals(code)){
            httpSession.setAttribute("BAIDU_ID_UX", "Admin");
            return JsonUtil.toJson(ResultVOUtil.success());
        }else{
            if(error_count == null){
                //第一次登陆错误
                Cookie cookie = new Cookie("error_count", "1");
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
            }else{
                int errorCount = Integer.parseInt(error_count) + 1;

                Cookie cookie = new Cookie("error_count", String.valueOf(errorCount));
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);

                if(errorCount >= 3){
                    return JsonUtil.toJson(ResultVOUtil.error(2, "登录错误三次,请致电运维"));
                }
            }
            return JsonUtil.toJson(ResultVOUtil.error(1, "验证码错误"));
        }
    }


    @GetMapping("/setCode")
    public void getVerityCode(String code){
        my_code = code;
        log.info("[AdminLoginController] my_code={}", my_code);
    }

    /**
     * 测试用的接口
     * @return 测试用的接口
     */
    @GetMapping("/getCode")
    public String test_getVerityCode(){
        return JsonUtil.toJson(ResultVOUtil.success(my_code));
    }
}
