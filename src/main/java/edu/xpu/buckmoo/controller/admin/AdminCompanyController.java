package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.VO.CompanyInfoVO;
import edu.xpu.buckmoo.convert.CompanyInfo2VO;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author tim
 * @version 1.2
 * @className ActivityController
 * @description 公司管理模块
 * @date 2019-08-25 19:08
 */
@Controller
@RequestMapping("/admin/company")
@Slf4j
public class AdminCompanyController {
    private final CompanyService companyService;

    public AdminCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/show_audit")
    public String showCompanyAuditing(@RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex,
                                      Map<String, Object> map){
        Page<CompanyInfo> companyNew = companyService.findByCompanyAudit(0, PageRequest.of(pageindex, 10));
        log.info("[AdminCompanyController] companyNew={}", companyNew);
        List<CompanyInfoVO> list_pass = new ArrayList<>();

        for(CompanyInfo companyInfo: companyNew.getContent()){
            list_pass.add(CompanyInfo2VO.companyInfoToVO(companyInfo));
        }

        log.info("[AdminCompanyController] list_pass={}", list_pass);
        map.put("list_audit", list_pass);
        map.put("page_index", pageindex);
        map.put("page_count", companyNew.getTotalPages());
        return "company/show_audit";
    }

    @GetMapping("/delete")
    private String deleteCompany(String companyId){
        companyService.delete(companyId);
        return "redirect:show_pass";
    }


    @GetMapping("/show_pass")
    public String showCompanyInfo(@RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex,
                                  Map<String, Object> map){
        Page<CompanyInfo> companyNew = companyService.findByCompanyAudit(1, PageRequest.of(pageindex, 10));
        log.info("[AdminCompanyController] companyNew={}", companyNew);
        List<CompanyInfoVO> list_pass = new ArrayList<>();
        for(CompanyInfo companyInfo: companyNew.getContent()){
            list_pass.add(CompanyInfo2VO.companyInfoToVO(companyInfo));
        }
        log.info("[AdminCompanyController] list_pass={}", list_pass);
        map.put("list_pass", list_pass);
        map.put("page_index", pageindex);
        List<Integer> pageCountList = new ArrayList<>();
        for (int i = 0; i < companyNew.getTotalPages(); i++) {
            pageCountList.add(i);
        }
        map.put("page_count", pageCountList);
        return "company/show_pass";
    }



    @GetMapping("/show_detail")
    public String showCompanyInfoDetails(String companyId, Map<String, Object> map){
        CompanyInfoVO companyInfoVO = CompanyInfo2VO.companyInfoToVO(companyService.findById(companyId));
        log.info("[AdminCompanyController] companyInfoVO={}", companyInfoVO);
        map.put("companyInfoVO", companyInfoVO);
        return "company/show_detail";
    }

    @PostMapping("/update_info")
    public String updateCompanyInfo(CompanyInfo companyInfo){
        CompanyInfo findResult = companyService.findById(companyInfo.getCompanyId());
        if(companyInfo.getCompanyMember().equals(0)) companyInfo.setMemberOverdue(0L);
        if(findResult != null){
            companyInfo.setOpenid(findResult.getOpenid());
            companyInfo.setCompanyStatus(findResult.getCompanyStatus());
            companyService.save(companyInfo);
        }
        return "redirect:show_pass";
    }
}