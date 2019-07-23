package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.repository.PartInfoRepository;
import edu.xpu.buckmoo.service.PageToPartInfoVO;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tim
 * @version 1.0
 * @className PartController
 * @description
 * @date 2019-06-19 22:53
 */
@RestController
@RequestMapping("/admin/part")
@Slf4j
public class AdminPartController {
    private final PartInfoRepository partInfoRep;

    private final PageToPartInfoVO pageToPartInfoVO;

    private final PartInfoService partInfoService;

    public AdminPartController(PartInfoRepository partInfoRep, PageToPartInfoVO pageToPartInfoVO, PartInfoService partInfoService) {
        this.partInfoRep = partInfoRep;
        this.pageToPartInfoVO = pageToPartInfoVO;
        this.partInfoService = partInfoService;
    }

    /**
     * 分状态查询兼职
     * @param status 状态参考 PartTimeStatusEnum.java
     * @param pageindex 分页索引
     * @return 查询结果列表
     */
    @GetMapping("/list")
    public String list(@RequestParam("status") Integer status, Integer pageindex){
        PageRequest pageRequest = PageRequest.of(pageindex, 10);
        Page<PartInfo> partInfoPage = partInfoRep.findAllByPartStatus(status, pageRequest);
        PartInfoVO partInfoVO = pageToPartInfoVO.partPageToPartInfoVO(partInfoPage);
        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    /**
     * 审核成功
     * @param partId 需要审核的兼职信息
     * @return 修改后的兼职信息
     */
    @GetMapping("/audit_success")
    public String audit_success(@RequestParam("partId") String partId){
        PartInfo partInfo = partInfoService.modifyPartStatus(partId, PartTimeStatusEnum.PASS_PAY.getCode());
        return JsonUtil.toJson(ResultVOUtil.success(partInfo));
    }

    /**
     * 审核失败
     * @param partId 需要审核的兼职信息
     * @return 修改后的兼职信息
     */
    @GetMapping("/audit_failed")
    public String audit_failed(@RequestParam("partId") String partId){
        partInfoService.modifyPartStatus(partId, PartTimeStatusEnum.NOT_PASS.getCode());
        return JsonUtil.toJson(ResultVOUtil.error(1, "审核未通过，已退款"));
    }
}