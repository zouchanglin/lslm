package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.VO.PartInfoOldVO;
import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.convert.PartTimeForm2Info;
import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.form.PartTimeForm;
import edu.xpu.buckmoo.service.PageToPartInfoVO;
import edu.xpu.buckmoo.service.PartCategoryService;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tim
 * @version 1.1
 * @className UserPartController
 * @description 用户兼职模块控制器
 * @date 2019-06-20 21:37
 */
@RestController
@Slf4j
@RequestMapping("/user/part")
public class UserPartController {
    private final PartInfoService partInfoService;

    private final PartCategoryService partCategoryService;

    private final PageToPartInfoVO pageToPartInfoVO;

    public UserPartController(PartCategoryService partCategoryService, PageToPartInfoVO pageToPartInfoVO, PartInfoService partInfoService) {
        this.partCategoryService = partCategoryService;
        this.pageToPartInfoVO = pageToPartInfoVO;
        this.partInfoService = partInfoService;
    }

    /**
     * 兼职信息列表
     * @version 1.0
     * @param pageindex 分页索引
     * @param category 类别Id
     */
    @GetMapping("/part_list")
    public String getPartInfo(@RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex,
                              @RequestParam(value = "category") Integer category){

        PageRequest pageRequest = PageRequest.of(pageindex, 4);
        Page<PartInfo> partInfoPage = partInfoService.listByCategoryAndStatus(category, PartTimeStatusEnum.PASS_PAY.getCode(), pageRequest);
        List<PartInfo> content = partInfoPage.getContent();
        PartInfoOldVO partInfoVO = new PartInfoOldVO();
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        partInfoVO.setPartInfoList(content);
        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    /**
     * 兼职分类列表
     * @version 1.0
     */
    @GetMapping("/category_list")
    public String getPartInfo(){
        List<PartCategory> categoryList = partCategoryService.getAll();
        return JsonUtil.toJson(ResultVOUtil.success(categoryList));
    }


    /**
     * 发布兼职
     * @version 1.0
     * @param openid cookie里面存储的openid
     * @param partTimeForm 兼职表单
     */
    @PostMapping("/create")
    public String createPartInfo(@CookieValue(value = "openid", required = false) String openid, PartTimeForm partTimeForm){
        PartInfo partInfo = PartTimeForm2Info.form2partInfo(partTimeForm);
        log.info("partInfo = {}", partInfo);
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "😁请先登录"));
        partInfo.setPartCreator(openid);
        PartInfo addRet = partInfoService.addOnePartTime(partInfo);

        if(addRet != null)
            return JsonUtil.toJson(ResultVOUtil.success(addRet));
        else
            return JsonUtil.toJson(ResultVOUtil.error(1, "网络繁忙"));
    }


    /**
     * 查看用户创建的兼职列表（不分状态的那种）
     * @version 1.1
     * @param openid openid
     * @param pageindex 分页参数
     * @return 用户创建的兼职列表
     */
    @GetMapping("/all_created")
    public String allCreatedPart(@CookieValue(value = "openid", required = false) String openid,
                                  @RequestParam("pageindex") Integer pageindex){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4, sort);
        Page<PartInfo> partInfoPage = partInfoService.userAllCreate(openid, pageRequest);

        PartInfoVO partInfoVO = pageToPartInfoVO.partPageToPartInfoVO(partInfoPage);
        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    /**
     * 查看用户接手的兼职列表（不分状态的那种）
     * @version 1.1
     * @param openid 用户Id
     * @param pageindex 分页参数
     * @return 用户接手的兼职列表
     */
    @GetMapping("/all_accepted")
    public String allAcceptPart(@CookieValue(value = "openid", required = false) String openid,
                                 @RequestParam("pageindex") Integer pageindex){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4, sort);
        Page<PartInfo> partInfoPage = partInfoService.userAllAccept(openid, pageRequest);

        PartInfoVO partInfoVO = pageToPartInfoVO.partPageToPartInfoVO(partInfoPage);
        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    /**
     * 查看用户发布的兼职列表（分状态查看）
     * @version 1.0
     * @param openid 用户openid
     * @param status 兼职信息的状态
     * @param pageindex 分页参数
     * @return 用户发布的兼职列表
     * @deprecated Use {@link #allCreatedPart(String, Integer)} instead
     */
    @GetMapping("/created_list")
    @Deprecated
    public String createdPartList(@CookieValue(value = "openid", required = false) String openid,
                                  @RequestParam("status") Integer status,
                                  @RequestParam("pageindex") Integer pageindex){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4, sort);
        Page<PartInfo> partInfoPage = partInfoService.listByUserCreate(openid, pageRequest, status);

        PartInfoOldVO partInfoVO = new PartInfoOldVO();
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        partInfoVO.setPartInfoList(partInfoPage.getContent());

        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    /**
     * 查看用户接手的兼职列表（分状态查看）
     * @version 1.0
     * @param openid 用户openid
     * @param status 兼职信息的状态
     * @param pageindex 分页参数
     * @return 用户接手的兼职列表
     * @deprecated Use {@link #allAcceptPart(String, Integer)} instead
     */
    @GetMapping("/accepted_list")
    public String acceptPartList(@CookieValue(value = "openid", required = false) String openid,
                                 @RequestParam("status") Integer status,
                                 @RequestParam("pageindex") Integer pageindex){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4, sort);
        Page<PartInfo> partInfoPage = partInfoService.listByUserAccept(openid, pageRequest, status);

        PartInfoOldVO partInfoVO = new PartInfoOldVO();
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        partInfoVO.setPartInfoList(partInfoPage.getContent());

        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }
}