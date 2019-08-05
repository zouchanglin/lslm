package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.VO.ActivityInfoVO;
import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.service.ActivityService;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className UserActivityController
 * @description 普通用户查看活动列表
 * @date 2019-06-20 21:39
 */
@RestController
@Slf4j
@RequestMapping("/user/activity")
public class UserActivityController {

    private final ActivityService activityService;

    private final CompanyService companyService;

    public UserActivityController(ActivityService activityService, CompanyService companyService) {
        this.activityService = activityService;
        this.companyService = companyService;
    }

    /**
     * 展示正在进行的活动
     * @return 正在进行的活动列表
     */
    @GetMapping("/list")
    public String list(){
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<ActivityInfo> byActivityAudit = activityService.findByActivityAudit(ActivityStatusEnum.PASS.getCode(), pageRequest);
        List<ActivityInfo> underwayActivity = byActivityAudit.getContent();
        List<ActivityInfoVO> retVO = new ArrayList<>();
        //需要使用活动的主办方Id、协办方Id查询对应的企业名称
        for(ActivityInfo activity: underwayActivity){
            ActivityInfoVO vo = new ActivityInfoVO();
            BeanUtils.copyProperties(activity, vo);
            CompanyInfo main = companyService.findById(activity.getActivityMain());
            if(main == null){
                log.error("【活动展示】活动对应主办方Id不正确");
                throw new BuckMooException(ResultEnum.ACTIVITY_ERROR);
            }
            vo.setActivityMainName(main.getCompanyName());
            retVO.add(vo);
        }
        return JsonUtil.toJson(ResultVOUtil.success(retVO));
    }
}
