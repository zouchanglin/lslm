package edu.xpu.buckmoo.controller.admin;

import com.lly835.bestpay.rest.type.Get;
import edu.xpu.buckmoo.VO.CompanyInfoVO;
import edu.xpu.buckmoo.VO.CompanyInfoVOStruct;
import edu.xpu.buckmoo.convert.CompanyInfo2VO;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author tim
 * @version 1.2
 * @className ActivityController
 * @description 公司管理模块
 * @date 2019-08-25 19:08
 */
@RestController
@RequestMapping("/admin/company")
@Slf4j
public class AdminCompanyController {
    private final CompanyService companyService;

    @Autowired
    private UserInfoService userInfoService;

    public AdminCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 查看正在审核的企业信息
     * @param pageindex 分页参数
     */
    @GetMapping("/show")
    public String showCompanyAuditing(@RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex,
                                      Integer status,
                                      HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        if(status == null){
            return JsonUtil.toJson(ResultVOUtil.error(1, "缺少参数：status"));
        }

        Page<CompanyInfo> companyNew = companyService.findByCompanyAudit(status, PageRequest.of(pageindex, 10));
        log.info("[AdminCompanyController] companyNew={}", companyNew);

        List<CompanyInfoVO> list_pass = new ArrayList<>();
        for(CompanyInfo companyInfo: companyNew.getContent()){
            list_pass.add(CompanyInfo2VO.companyInfoToVO(companyInfo));
        }
        log.info("[AdminCompanyController] list_pass={}", list_pass);
        CompanyInfoVOStruct companyInfoVOStruct = new CompanyInfoVOStruct();
        companyInfoVOStruct.setListPass(list_pass);
        companyInfoVOStruct.setPageCount(companyNew.getTotalPages());
        companyInfoVOStruct.setPageIndex(pageindex);
        return JsonUtil.toJson(ResultVOUtil.success(companyInfoVOStruct));
    }


    @GetMapping("/show_all")
    public String showAllCompany(@RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex,
                                 HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        Page<CompanyInfo> companyNew = companyService.findAll(PageRequest.of(pageindex, 10));

        log.info("[AdminCompanyController] companyNew={}", companyNew);

        List<CompanyInfoVO> list_pass = new ArrayList<>();
        for(CompanyInfo companyInfo: companyNew.getContent()){
            list_pass.add(CompanyInfo2VO.companyInfoToVO(companyInfo));
        }
        log.info("[AdminCompanyController] list_pass={}", list_pass);
        CompanyInfoVOStruct companyInfoVOStruct = new CompanyInfoVOStruct();
        companyInfoVOStruct.setListPass(list_pass);
        companyInfoVOStruct.setPageCount(companyNew.getTotalPages());
        companyInfoVOStruct.setPageIndex(pageindex);
        return JsonUtil.toJson(ResultVOUtil.success(companyInfoVOStruct));
    }

    @GetMapping("/delete")
    private String deleteCompany(String companyId,
                                 HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        companyService.delete(companyId);
        return JsonUtil.toJson(ResultVOUtil.success());
    }

    @GetMapping("/audit")
    private String auditCompany(String companyId,
                                 Integer status,
                                 HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }
        CompanyInfo auditResult = companyService.audit(companyId, status);
        log.info("修改状态信息后 auditResult={}", auditResult);
        return JsonUtil.toJson(ResultVOUtil.success(CompanyInfo2VO.companyInfoToVO(auditResult)));
    }


    @GetMapping("/show_detail")
    public String showCompanyInfoDetails(String companyId, Map<String, Object> map,
                                         HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        CompanyInfoVO companyInfoVO = CompanyInfo2VO.companyInfoToVO(companyService.findById(companyId));
        log.info("[AdminCompanyController] companyInfoVO={}", companyInfoVO);
        return JsonUtil.toJson(ResultVOUtil.success(companyInfoVO));
    }

    @PostMapping("/update_info")
    public String updateCompanyInfo(CompanyInfo companyInfo,
                                    HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }
        CompanyInfo findResult = companyService.findById(companyInfo.getCompanyId());
        if(companyInfo.getCompanyMember().equals(0)) companyInfo.setMemberOverdue(0L);
        if(findResult != null){
            companyInfo.setOpenid(findResult.getOpenid());
            companyInfo.setCompanyStatus(findResult.getCompanyStatus());
            CompanyInfo saveResult = companyService.save(companyInfo);
            return JsonUtil.toJson(ResultVOUtil.success(CompanyInfo2VO.companyInfoToVO(saveResult)));
        }
        return JsonUtil.toJson(ResultVOUtil.error(1, "companyId错误，无此信息"));
    }


}