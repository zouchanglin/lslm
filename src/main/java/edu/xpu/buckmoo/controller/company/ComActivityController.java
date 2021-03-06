package edu.xpu.buckmoo.controller.company;

import edu.xpu.buckmoo.VO.ActivityInfoVO;
import edu.xpu.buckmoo.VO.ActivityVOStruct;
import edu.xpu.buckmoo.convert.ActivityInfo2VO;
import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.form.ActivityForm;
import edu.xpu.buckmoo.service.ActivityService;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.EnumUtil;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tim
 * @version 1.1
 * @className ActivityController
 * @description 活动管理模块
 * @date 2019-08-20 19:08
 */
@RestController
@RequestMapping("/company/activity")
@Slf4j
public class ComActivityController {
    private final CompanyService companyService;
    private final ActivityService activityService;
    private final ActivityInfo2VO activityInfo2VO;

    public ComActivityController(CompanyService companyService, ActivityService activityService, ActivityInfo2VO activityInfo2VO) {
        this.companyService = companyService;
        this.activityService = activityService;
        this.activityInfo2VO = activityInfo2VO;
    }


    @PostMapping("/update")
    public String activityUpdate(@CookieValue(value = "openid", required = false) String openid,
                                 String activityId,
                                 @ModelAttribute ActivityForm activityForm){
        //先判断openid
        if(openid == null || "".equals(openid))
            return JsonUtil.toJson(ResultVOUtil.error(1, "请先登录"));
        //通过openid查找企业信息
        CompanyInfo findResult = companyService.findByOpenid(openid);

        //未找到企业信息进行错误码返回
        if(findResult == null)
            return JsonUtil.toJson(ResultVOUtil.error(2, "尚未注册公司"));


        //构建活动信息实体
        ActivityInfo activityInfo = new ActivityInfo();
        //表单信息到实体对象
        BeanUtils.copyProperties(activityForm, activityInfo);

        ActivityInfo activityFindResult = activityService.findOne(activityId);
        if(activityFindResult == null){
            return JsonUtil.toJson(ResultVOUtil.error(5, "不存在此活动"));
        }

        //修改的活动是已经通过的活动
        if(activityFindResult.getActivityAudit().equals(ActivityStatusEnum.PASS.getCode())){
            return JsonUtil.toJson(ResultVOUtil.error(3, "已通过的活动无法修改"));
        }

        //修改的不是自己的活动
        if(!activityFindResult.getActivityOpenid().equals(openid)){
            return JsonUtil.toJson(ResultVOUtil.error(4, "权限异常"));
        }

        activityInfo.setActivityId(activityFindResult.getActivityId());
        activityInfo.setActivityOpenid(activityFindResult.getActivityOpenid());
        activityInfo.setActivityAudit(activityFindResult.getActivityAudit());

        ActivityInfo saveResult = activityService.save(activityInfo);
        return JsonUtil.toJson(ResultVOUtil.success(saveResult));
    }


    @GetMapping("/delete")
    public String activityDelete(@CookieValue(value = "openid", required = false) String openid,
                                 String activityId){
        //先判断openid
        if(openid == null || "".equals(openid))
            return JsonUtil.toJson(ResultVOUtil.error(1, "请先登录"));
        if(activityId == null)
            return JsonUtil.toJson(ResultVOUtil.error(2, "参数不完整"));
        if(activityService.delete(openid, activityId))
            return JsonUtil.toJson(ResultVOUtil.success());
        return JsonUtil.toJson(ResultVOUtil.error(3, "权限错误"));
    }

    @GetMapping("/show")
    public String activityShow(@CookieValue(value = "openid", required = false) String openid,
                               @RequestParam(value = "pageindex", defaultValue = "0") Integer pageindex){
        //先判断openid
        if(openid == null || "".equals(openid))
            return JsonUtil.toJson(ResultVOUtil.error(1, "请先登录"));

        //通过openid查找企业信息
        CompanyInfo findResult = companyService.findByOpenid(openid);

        //未找到企业信息进行错误码返回
        if(findResult == null) return JsonUtil.toJson(ResultVOUtil.error(2, "尚未注册公司"));

        Sort sort = new Sort(Sort.Direction.DESC, "activityId");
        PageRequest pageRequest = PageRequest.of(pageindex, 5, sort);

        Page<ActivityInfo> activityInfoPage = activityService.myAllActivity(openid, pageRequest);

        List<ActivityInfo> content = activityInfoPage.getContent();
        List<ActivityInfoVO> list = new ArrayList<>();
        for(ActivityInfo activityInfo: content){
            list.add(activityInfo2VO.activityToVO(activityInfo));
        }

        ActivityVOStruct activityVOList = new ActivityVOStruct();
        activityVOList.setCount(content.size());
        activityVOList.setList(list);
        activityVOList.setPageCount(activityInfoPage.getTotalPages());
        activityVOList.setCurrentPage(pageindex);
        return JsonUtil.toJson(ResultVOUtil.success(activityVOList));
    }

    /**
     * 企业用户创建活动
     * @param openid 企业用户Id
     * @param activityForm 活动表单
     * @return 保存的活动信息
     */
    @PostMapping("/create")
    public String createActivity(@CookieValue(value = "openid", required = false) String openid,
                                 @ModelAttribute ActivityForm activityForm){
        //先判断openid
        if(openid == null || "".equals(openid))
            return JsonUtil.toJson(ResultVOUtil.error(1, "请先登录"));

        //通过openid查找企业信息
        try{
            CompanyInfo findResult = companyService.findByOpenid(openid);
            log.info("findResult = {}", findResult);
            if(!findResult.getCompanyStatus().equals(CompanyStatusEnum.PASS.getCode())){
                Integer companyStatus = findResult.getCompanyStatus();
                if(companyStatus.equals(CompanyStatusEnum.NEW.getCode())){
                    return JsonUtil.toJson(ResultVOUtil.error(2, "审核中暂时不能发布活动"));
                }else if(companyStatus.equals(CompanyStatusEnum.NOT_PASS.getCode())){
                    return JsonUtil.toJson(ResultVOUtil.error(3, "审核未通过"));
                }
                return JsonUtil.toJson(ResultVOUtil.error(companyStatus, EnumUtil.getByCode(companyStatus, CompanyStatusEnum.class).getMessage()));
            }
        }catch (Exception e){
            //未找到企业信息进行错误码返回
            return JsonUtil.toJson(ResultVOUtil.error(4, "尚未注册企业"));
        }

        //构建活动信息实体
        ActivityInfo activityInfo = new ActivityInfo();

        //表单信息到实体对象
        BeanUtils.copyProperties(activityForm, activityInfo);
        activityInfo.setActivityId(KeyUtil.genUniqueKey());
        activityInfo.setActivityOpenid(openid);
        activityInfo.setActivityAudit(ActivityStatusEnum.NEW.getCode());

        //创建相关订单，并返回保存后的活动信息
        ActivityInfo createResult = activityService.create(activityInfo);
        return JsonUtil.toJson(ResultVOUtil.success(createResult));
    }
}