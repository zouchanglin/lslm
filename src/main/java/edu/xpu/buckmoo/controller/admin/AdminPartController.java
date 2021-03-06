package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.VO.ResultVO;
import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.repository.PartCategoryRepository;
import edu.xpu.buckmoo.repository.order.PartInfoRepository;
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

import javax.servlet.http.HttpSession;

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
    private final PartCategoryRepository categoryRepository;

    public AdminPartController(PartInfoRepository partInfoRep, PageToPartInfoVO pageToPartInfoVO, PartInfoService partInfoService, PartCategoryRepository categoryRepository) {
        this.partInfoRep = partInfoRep;
        this.pageToPartInfoVO = pageToPartInfoVO;
        this.partInfoService = partInfoService;
        this.categoryRepository = categoryRepository;
    }

    /**
     * 分状态查询兼职
     * @param status 状态参考 PartTimeStatusEnum.java
     * @param pageindex 分页索引
     * @return 查询结果列表
     */
    @GetMapping("/list")
    public String list(@RequestParam("status") Integer status, Integer pageindex,
                       HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }
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
    public String audit_success(@RequestParam("partId") String partId,
                                HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        PartInfo partInfo = partInfoService.modifyPartStatus(partId, PartTimeStatusEnum.PASS_PAY.getCode());
        return JsonUtil.toJson(ResultVOUtil.success(partInfo));

    }

    /**
     * 审核失败
     * @param partId 需要审核的兼职信息
     * @return 修改后的兼职信息
     */
    @GetMapping("/audit_failed")
    public String audit_failed(@RequestParam("partId") String partId,
                               HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        partInfoService.modifyPartStatus(partId, PartTimeStatusEnum.NOT_PASS.getCode());
        return JsonUtil.toJson(ResultVOUtil.error(1, "审核未通过，已退款"));
    }

    @GetMapping("/addPartCategory")
    public ResultVO addPartCategory(@RequestParam(value = "category_name", required = false) String category_name,
                                            HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return ResultVOUtil.error(1, "登录信息已经过期");
        }
        if(category_name == null){
            return ResultVOUtil.error(2, "参数不完成");
        }
        PartCategory newPartCategory = new PartCategory();
        newPartCategory.setCategoryName(category_name);
        newPartCategory.setCreateTime(System.currentTimeMillis());
        newPartCategory.setUpdateTime(System.currentTimeMillis());

        PartCategory partCategory = categoryRepository.save(newPartCategory);
        log.info("[AdminPartController] addPartCategory partCategory={}", partCategory);
        return ResultVOUtil.success(partCategory);
    }
}