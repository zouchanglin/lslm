package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/admin/count")
@RestController
public class AdminCountController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CompanyService companyService;
    @GetMapping("/peopleCount")
    public String peopleCount(HttpSession httpSession){
        String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
        if(BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin")) return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));

        Integer people = userInfoService.userCount();
        return JsonUtil.toJson(ResultVOUtil.success(people));
    }
    @GetMapping("/companyCount")
    public String companyCount(HttpSession httpSession){
        String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
        if(BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin")) return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));

        Integer people = companyService.companyCount();
        return JsonUtil.toJson(ResultVOUtil.success(people));
    }

}
