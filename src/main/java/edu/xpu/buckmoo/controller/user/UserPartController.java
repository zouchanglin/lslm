package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.VO.ResultVO;
import edu.xpu.buckmoo.convert.PartTimeForm2Info;
import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.form.PartTimeForm;
import edu.xpu.buckmoo.service.PartCategoryService;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className UserPartController
 * @description
 * @date 2019-06-20 21:37
 */
@RestController
@Slf4j
@RequestMapping("/user/part")
public class UserPartController {
    @Autowired
    private PartInfoService partInfoService;

    @Autowired
    private PartCategoryService partCategoryService;

    /**
     * 兼职信息列表
     * @param pageindex 分页索引
     * @param category 类别Id
     */
    @GetMapping("/part_list")
    public String getPartInfo(@RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex,
                              @RequestParam(value = "category") Integer category){

        PageRequest pageRequest = PageRequest.of(pageindex, 4);
        Page<PartInfo> partInfoPage = partInfoService.listByCategoryAndStatus(category, PartTimeStatusEnum.PASS_PAY.getCode(), pageRequest);
        List<PartInfo> content = partInfoPage.getContent();
        PartInfoVO partInfoVO = new PartInfoVO();
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        partInfoVO.setPartInfoList(content);
        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    /**
     * 兼职分类列表
     */
    @GetMapping("/category_list")
    public String getPartInfo(){
        List<PartCategory> categoryList = partCategoryService.getAll();
        return JsonUtil.toJson(ResultVOUtil.success(categoryList));
    }


    /**
     * 发布兼职
     * @param openid cookie里面存储的openid
     * @param partTimeForm 兼职表单
     */
    @PostMapping("/create")
    public String createPartInfo(@CookieValue(value = "openid", required = false) String openid, PartTimeForm partTimeForm){
        PartInfo partInfo = PartTimeForm2Info.form2partInfo(partTimeForm);
        log.info("partInfo = {}", partInfo);
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));
        partInfo.setPartCreator(openid);
        PartInfo addRet = partInfoService.addOnePartTime(partInfo);

        if(addRet != null)
            return JsonUtil.toJson(ResultVOUtil.success(addRet));
        else
            return JsonUtil.toJson(ResultVOUtil.error(1, "网络繁忙"));
    }

    //TODO 支付兼职接口
    @PostMapping("/pay")
    public String payMyOrder(@CookieValue(value = "openid", required = false) String openid,
                             String partInfoId){
        return null;
    }

    @GetMapping("/created_list")
    public String createdPartList(@CookieValue(value = "openid", required = false) String openid,
                                  @RequestParam("status") Integer status,
                                  @RequestParam("pageindex") Integer pageindex){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4, sort);
        Page<PartInfo> partInfoPage = partInfoService.listByUserCreate(openid, pageRequest, status);

        PartInfoVO partInfoVO = new PartInfoVO();
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        partInfoVO.setPartInfoList(partInfoPage.getContent());

        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    @GetMapping("/accepted_list")
    public String acceptPartList(@CookieValue(value = "openid", required = false) String openid,
                                  @RequestParam("status") Integer status,
                                  @RequestParam("pageindex") Integer pageindex){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4, sort);
        Page<PartInfo> partInfoPage = partInfoService.listByUserAccept(openid, pageRequest, status);

        PartInfoVO partInfoVO = new PartInfoVO();
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        partInfoVO.setPartInfoList(partInfoPage.getContent());

        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }
}
