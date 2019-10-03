package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.VO.PartInfoOldVO;
import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.VO.ResultVO;
import edu.xpu.buckmoo.convert.PartTimeForm2Info;
import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.form.PartTimeForm;
import edu.xpu.buckmoo.service.PageToPartInfoVO;
import edu.xpu.buckmoo.service.PartCategoryService;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tim
 * @version 1.3
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
    private final UserInfoService userInfoService;

    public UserPartController(PartCategoryService partCategoryService, PageToPartInfoVO pageToPartInfoVO,
                              PartInfoService partInfoService, UserInfoService userInfoService) {
        this.partCategoryService = partCategoryService;
        this.pageToPartInfoVO = pageToPartInfoVO;
        this.partInfoService = partInfoService;
        this.userInfoService = userInfoService;
    }

    /**
     * 兼职信息列表
     * @version 1.1
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
     * @version 1.2
     */
    @GetMapping("/category_list")
    public String getPartInfo(){
        List<PartCategory> categoryList = partCategoryService.getAll();
        return JsonUtil.toJson(ResultVOUtil.success(categoryList));
    }


    /**
     * 用户发布兼职
     * @version 1.2
     * @param openid cookie里面存储的openid
     * @param partTimeForm 兼职表单
     */
    @PostMapping("/create")
    public String createPartInfo(@CookieValue(value = "openid", required = false) String openid,
                                 PartTimeForm partTimeForm){
        PartInfo partInfo = PartTimeForm2Info.form2partInfo(partTimeForm);
        log.info("[UserPartController] partInfo = {}", partInfo);
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));


        partInfo.setPartCreator(openid);
        //在保存的时候生成统一订单
        PartInfo addRet = partInfoService.addOnePartTime(partInfo);

        if(addRet != null)
            return JsonUtil.toJson(ResultVOUtil.success(addRet));
        else
            return JsonUtil.toJson(ResultVOUtil.error(1, "网络繁忙"));
    }

    /**
     * 用户接受兼职
     * @param openid 用户的openid
     * @param partId 兼职id
     * @return 更新后的兼职信息
     */
    @PostMapping("/accept")
    public String acceptPartInfo(@CookieValue(value = "openid", required = false) String openid,
                             String partId){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));

        //检查接单人电话信息
        UserInfo byId = userInfoService.findById(openid);
        String userPhone = byId.getUserPhone();
        //手机号正则
        if(userPhone == null || userPhone.trim().equals("")){
            return JsonUtil.toJson(ResultVOUtil.error(1, "请先绑定手机号"));
        }

        PartInfo updatePartInfo = partInfoService.acceptOnePart(openid, partId);
        return JsonUtil.toJson(ResultVOUtil.success(updatePartInfo));
    }

    /**
     * 接受者完成兼职
     * @param openid 接受者openid
     * @param partId 兼职id
     * @return 更新状态后的信息
     */
    @PostMapping("/accepter_finish")
    public String acceptFinishPart(@CookieValue(value = "openid", required = false) String openid,
                                   String partId){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));

        PartInfo partInfo = partInfoService.finishOnePart(openid, partId);
        return JsonUtil.toJson(partInfo);
    }
    
    @PostMapping("/affirm_finish")
    public String affirmFinishPart(@CookieValue(value = "openid", required = false) String openid,
                                   String partId){
        if(openid == null) return JsonUtil.toJson(ResultVOUtil.error(2, "请先登录"));

        PartInfo updatePartInfo = partInfoService.affirmFinishPart(openid, partId);

        //TODO 付款到账户(90-days)
        return JsonUtil.toJson(ResultVOUtil.success(updatePartInfo));
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
        //Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4);
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
        //Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        PageRequest pageRequest = PageRequest.of(pageindex, 4);
        Page<PartInfo> partInfoPage = partInfoService.userAllAccept(openid, pageRequest);

        PartInfoVO partInfoVO = pageToPartInfoVO.partPageToPartInfoVO(partInfoPage);
        return JsonUtil.toJson(ResultVOUtil.success(partInfoVO));
    }

    /**
     * 用户删除兼职信息
     * @param partId 兼职信息id
     */
    @GetMapping("/delete_part")
    public ResultVO deleteMyPart(@CookieValue(value = "openid", required = false) String openid,
                                 String partId){
        if(openid == null) return ResultVOUtil.error(1, "请先登录");
        if(partId == null) return ResultVOUtil.error(2, "请完善参数");
        //判断兼职存在与否
        PartInfo partInfo = partInfoService.findOneById(partId);

        if(partInfo == null){
            return ResultVOUtil.error(3, "不存在此兼职信息");
        }

        if(!openid.equals(partInfo.getPartCreator())) {
            return ResultVOUtil.error(4, "无此权限");
        }
        //判断兼职的状态
        Integer partStatus = partInfo.getPartStatus();
        if(partStatus.equals(PartTimeStatusEnum.PASS_PAY.getCode())){
            //别人已经接单，无法删除
            return ResultVOUtil.error(5, "有人已经接单，无法删除");
        }
        //其他情况
        partInfoService.deleteMyPart(openid, partId);
        return ResultVOUtil.success();
    }


    @GetMapping("/all_part")
    public ResultVO allPart(@RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex){
        PageRequest pageRequest = PageRequest.of(pageindex, 4);
        Page<PartInfo> partInfoPage = partInfoService.listByStatus(PartTimeStatusEnum.PASS_PAY.getCode(), pageRequest);
        PartInfoOldVO partInfoVO = new PartInfoOldVO();
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        partInfoVO.setPartInfoList(partInfoPage.getContent());
        return ResultVOUtil.success(partInfoVO);
    }
}