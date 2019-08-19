package edu.xpu.buckmoo.controller.company;

import edu.xpu.buckmoo.convert.CompanyForm2CompanyInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.form.CompanyForm;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author tim
 * @version 1.2
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


    @GetMapping("/show")
    public String companyInfoShow(@CookieValue(value = "openid") String openid){
        CompanyInfo byOpenid = companyService.findByOpenid(openid);
        if(byOpenid == null){
            return JsonUtil.toJson(ResultVOUtil.error(1, "未注册公司"));
        }
        return JsonUtil.toJson(ResultVOUtil.success(byOpenid));
    }
    /**
     * 公司登陆
     * @return 设置Cookie并和登录状态
     */
    @PostMapping("/login")
    public String companyLogin(@CookieValue(value = "openid") String openid){
        CompanyInfo byOpenid = companyService.findByOpenid(openid);
        if(byOpenid == null){
            return JsonUtil.toJson(ResultVOUtil.error(1, "未注册公司"));
        }
        return JsonUtil.toJson(ResultVOUtil.error(0, "登录成功"));
    }

    /**
     * 公司信息注册
     * @param companyForm 公司信息
     * @return 保存后的公司信息JSON
     */
    @PostMapping("/register")
    public String companyRegister(@ModelAttribute CompanyForm companyForm,
                                  @CookieValue(value = "openid") String openid){
        CompanyInfo companyInfo = CompanyForm2CompanyInfo.form2info(companyForm);
        companyInfo.setOpenid(openid);
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
    public String companyUpdate(@ModelAttribute CompanyForm companyForm,
                                @CookieValue(value = "openid") String openid){
        CompanyInfo companyInfo = CompanyForm2CompanyInfo.form2info(companyForm);
        companyInfo.setOpenid(openid);
        CompanyInfo registerResult = companyService.save(companyInfo);
        if(registerResult != null){
            log.info("registerResult={}", registerResult);
            return JsonUtil.toJson(ResultVOUtil.success(registerResult));
        }else{
            return JsonUtil.toJson(ResultVOUtil.error(1, "更新信息不正确"));
        }
    }
}