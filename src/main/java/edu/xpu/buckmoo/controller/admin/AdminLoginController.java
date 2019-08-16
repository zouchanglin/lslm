package edu.xpu.buckmoo.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @PostMapping("/")
    public String adminLogin(@CookieValue(value = "error", required = false) String error,
                             String phone, String password, HttpServletRequest request, HttpServletResponse response){

        if(error == null && phone.equals("15291418231") && password.equals("123456")){

        }
        return "";
    }
}
