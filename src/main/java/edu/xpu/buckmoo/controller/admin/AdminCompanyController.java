package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.VO.ResultVO;
import edu.xpu.buckmoo.convert.ActivityForm2ActivityInfo;
import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.form.ActivityForm;
import edu.xpu.buckmoo.form.CompanyForm;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.EnumUtil;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author tim
 * @version 1.0
 * @className ActivityController
 * @description 公司管理模块
 * @date 2019-06-19 19:08
 */
@RestController
@RequestMapping("/admin/company")
@Slf4j
public class AdminCompanyController {
    private final CompanyService companyService;

    public AdminCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 管理员查看活动
     * @param status 活动类型
     * @return 符合条件的活动列表
     */
    @GetMapping("/show")
    public ResultVO showCompanyInfo(@RequestParam(value = "status", defaultValue = "0")Integer status,
                                    @RequestParam(value = "pageindex", defaultValue = "0")Integer pageindex){
        if(EnumUtil.getByCode(status, CompanyStatusEnum.class) == null){
            log.error("CompanyStatus={}", status);
            throw new BuckMooException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(pageindex, 10);
        Page<CompanyInfo> companyAudit = companyService.findByCompanyAudit(status, pageRequest);

        return ResultVOUtil.success(companyAudit.getContent());
    }

    @RequestMapping("/delete")
    public ResultVO deleteCompany(@RequestParam("companyId") String companyId){
        CompanyInfo one = companyService.findOne(companyId);
        if(one == null) throw new BuckMooException(ResultEnum.ACTIVITY_ERROR);
        companyService.delete(companyId);
        return ResultVOUtil.success();
    }

    /**
     * 更新企业信息
     * @param companyId 企业信息Id
     * @param companyForm 企业信息新表单
     * @return 更新的企业信息结果
     */
    @RequestMapping("/update")
    public ResultVO updateCompany(@RequestParam("companyId") String companyId,
                                  CompanyForm companyForm){
        CompanyInfo companyInfo = companyService.findOne(companyId);
        BeanUtils.copyProperties(companyForm, companyInfo);
        //TODO 可能有BUG
        log.info("companyInfo={}", companyInfo);
        CompanyInfo saveRet = companyService.save(companyInfo);
        return ResultVOUtil.success(saveRet);
    }
}