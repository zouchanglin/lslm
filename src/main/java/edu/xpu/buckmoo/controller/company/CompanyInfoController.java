package edu.xpu.buckmoo.controller.company;

import edu.xpu.buckmoo.convert.CompanyForm2CompanyInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.form.CompanyForm;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tim
 * @version 1.1
 * @className CompanyInfoController
 * @description 公司信息的控制器（登录、注册、信息更新）
 * @date 2019-06-20 21:33
 */
@RestController
@RequestMapping("/company/info")
@Slf4j
public class CompanyInfoController {
    private final CompanyService companyService;

    public CompanyInfoController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 公司登陆
     * @param companyId 公司Id
     * @param password 密码
     * @return 设置Cookie并和登录状态
     */
    @PostMapping("/login")
    public String companyLogin(String companyId, String password,
                               HttpServletResponse response,
                               @CookieValue(required = false, value = "login_error") String login_error){
        //判断之前的登录错误次数
        if(login_error != null){
            int errorCount = Integer.parseInt(login_error);
            if(errorCount >= 2){
                return JsonUtil.toJson(ResultVOUtil.error(2, "密码错误超过3次，请10分钟后再试"));
            }
        }

        CompanyInfo companyInfo = companyService.findById(companyId);

        if(companyInfo != null && companyInfo.getLoginPassword().equals(password)){
            //说明登陆成功
            Cookie cookie = new Cookie("company", companyInfo.getCompanyId());
            response.addCookie(cookie);

            //清除登陆失败的Cookie
            Cookie failed = new Cookie("login_error", "0");
            failed.setMaxAge(0);
            response.addCookie(failed);
            return JsonUtil.toJson(ResultVOUtil.error(0, "登陆成功"));
        }else{
            Cookie cookie;
            if(login_error == null)
                cookie = new Cookie("login_error", "0");
            else
                cookie = new Cookie("login_error", String.valueOf((Integer.parseInt(login_error) + 1)));
            cookie.setMaxAge(600);
            response.addCookie(cookie);

            //登陆失败
            return JsonUtil.toJson(ResultVOUtil.error(1, "用户名或者密码错误"));
        }
    }

    /**
     * 公司信息注册
     * @param companyForm 公司信息
     * @return 保存后的公司信息JSON
     */
    @PostMapping("/register")
    public String companyRegister(@ModelAttribute CompanyForm companyForm){
        CompanyInfo companyInfo = CompanyForm2CompanyInfo.form2info(companyForm);
        CompanyInfo registerResult = companyService.register(companyInfo);
        if(registerResult != null){
            log.info("registerResult={}", registerResult);
            return JsonUtil.toJson(ResultVOUtil.success(registerResult));
        }else{
            return JsonUtil.toJson(ResultVOUtil.error(1, "注册信息不正确"));
        }
    }

    /**
     * 公司信息更新
     * @param companyForm 更新的公司信息
     * @return 保存后的公司信息JSON
     */
    @PostMapping("/update")
    public String companyUpdate(@ModelAttribute CompanyForm companyForm){
        CompanyInfo companyInfo = CompanyForm2CompanyInfo.form2info(companyForm);
        CompanyInfo registerResult = companyService.save(companyInfo);
        if(registerResult != null){
            log.info("registerResult={}", registerResult);
            return JsonUtil.toJson(ResultVOUtil.success(registerResult));
        }else{
            return JsonUtil.toJson(ResultVOUtil.error(1, "更新信息不正确"));
        }
    }
}