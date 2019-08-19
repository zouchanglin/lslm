package edu.xpu.buckmoo.controller.company;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.form.ActivityForm;
import edu.xpu.buckmoo.service.ActivityService;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author tim
 * @version 1.0
 * @className ActivityController
 * @description 活动管理模块
 * @date 2019-06-19 19:08
 */
@RestController
@RequestMapping("/company/activity")
@Slf4j
public class ComActivityController {
    private final CompanyService companyService;
    private final ActivityService activityService;

    public ComActivityController(CompanyService companyService, ActivityService activityService) {
        this.companyService = companyService;
        this.activityService = activityService;
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
        CompanyInfo findResult = companyService.findByOpenid(openid);

        //未找到企业信息进行错误码返回
        if(findResult == null)
            return JsonUtil.toJson(ResultVOUtil.error(2, "尚未注册公司"));

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