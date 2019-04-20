package lishan.live.lslm.controller;

import lishan.live.lslm.entity.CompanyInfo;
import lishan.live.lslm.enums.ResultEnum;
import lishan.live.lslm.exception.CompanyException;
import lishan.live.lslm.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CompanyController
 * @Description 企业信息管理的Controller
 * @Author tim
 * @Date 2019-04-19 12:22
 * @Version 1.0
 */
@Controller
@Slf4j
@RequestMapping(value = "/admin/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 查找所有Company或者根据条件查询
     * @param map 填充的数据
     * @param companyName 名称相似查找的条件
     * @return 查询结果集合
     */
    @GetMapping("/show")
    public String showTableCompany(Map<String, Object> map,
                                   @RequestParam(value = "companyName", required = false) String companyName){
        List<CompanyInfo> companyList;
        if(companyName == null){
            companyList = companyService.findAllCompanyInfo();
        }else{
            companyList = companyService.findAllCompanyInfoNameLike(companyName);
        }
        log.info("[AdminController]: companyList.size() = {}", companyList.size());
        map.put("companies", companyList);
        return "admin-company";
    }

    @GetMapping("/update")
    public String updateCompanyInfo(@RequestParam(value = "companyId")String companyId,
                                Map<String, Object> map){
        CompanyInfo company = companyService.findCompanyInfoById(companyId);
        if(!companyId.equals(company.getCompanyId())){
            //如果公司信息不存在就抛出异常，因为这个是修改的接口
            throw new CompanyException(ResultEnum.ENTITY_NOT_EXIST);
        }
        map.put("company", company);
        return "company/company-update";
    }

    @GetMapping("/delete")
    public String deleteCompanyInfo(@RequestParam("companyId")String companyId){
        companyService.deleteCompanyInfo(companyId);
        return "success";
    }

    @PostMapping("/add")
    public String addCompanyInfo(@ModelAttribute CompanyInfo companyInfo){
        CompanyInfo saveCompanyInfo = companyService.saveCompanyInfo(companyInfo);
        return saveCompanyInfo != null ? "success":"error";
    }

    @RequestMapping("/update-info")
    public String updateCompanyInfo(@ModelAttribute CompanyInfo companyInfo){
        if(companyInfo == null) return "error";
        log.info("【页面传回CompanyInfo】{}", companyInfo);
        //收到修改后的数据
        CompanyInfo saveRet = companyService.saveCompanyInfo(companyInfo);
        return saveRet != null ? "success":"error";
    }

    /**
     * 绑定页面传递时间字符串数据给java.util.Date类型
     */
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }
}
