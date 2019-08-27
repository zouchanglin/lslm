package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/count")
@RestController
public class AdminCountController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CompanyService companyService;
    @GetMapping("/peopleCount")
    public String peopleCount(){
        Integer people = userInfoService.userCount();
        return JsonUtil.toJson(ResultVOUtil.success(people));
    }
    @GetMapping("/companyCount")
    public String companyCount(){
        Integer people = companyService.companyCount();
        return JsonUtil.toJson(ResultVOUtil.success(people));
    }

}
