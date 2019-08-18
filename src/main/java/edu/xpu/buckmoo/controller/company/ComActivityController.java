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
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ActivityService activityService;

    @PostMapping("/create")
    public String createActivity(@CookieValue("openid") String openid,
                                 @ModelAttribute ActivityForm activityForm){
        if(openid == null || "".equals(openid)){
            return JsonUtil.toJson(ResultVOUtil.error(1, "请先登录"));
        }
        CompanyInfo findResult = companyService.findByOpenid(openid);
        if(findResult == null){
            return JsonUtil.toJson(ResultVOUtil.error(2, "尚未注册公司"));
        }

        //构建活动信息实体
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityForm, activityInfo);
        activityInfo.setActivityId(KeyUtil.genUniqueKey());
        activityInfo.setActivityOpenid(openid);
        activityInfo.setActivityAudit(ActivityStatusEnum.NEW.getCode());

        ActivityInfo createResult = activityService.create(activityInfo);
        return  null;
    }
}