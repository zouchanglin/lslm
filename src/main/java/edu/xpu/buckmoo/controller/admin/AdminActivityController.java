package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.VO.ResultVO;
import edu.xpu.buckmoo.convert.ActivityForm2ActivityInfo;
import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.form.ActivityForm;
import edu.xpu.buckmoo.service.ActivityService;
import edu.xpu.buckmoo.utils.EnumUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className ActivityController
 * @description 活动管理模块
 * @date 2019-06-19 19:08
 */
@RestController
@RequestMapping("/admin/activity")
@Slf4j
public class AdminActivityController {
    private final ActivityService activityService;

    public AdminActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * 管理员查看活动
     * @param type 活动类型
     * @return 符合条件的活动列表
     */
    @GetMapping("/show")
    public ResultVO showActivityInfo(@RequestParam("type")Integer type){
        if(EnumUtil.getByCode(type, ActivityStatusEnum.class) == null){
            log.error("ActivityType={}", type);
            throw new BuckMooException(ResultEnum.PARAM_ERROR);
        }
        List<ActivityInfo> activityListByStatus = activityService.findByActivityAudit(type);
        return ResultVOUtil.success(activityListByStatus);
    }


    @RequestMapping("/delete")
    public ResultVO deleteActivity(@RequestParam("activityId") String activityId){
        ActivityInfo one = activityService.findOne(activityId);
        if(one == null) throw new BuckMooException(ResultEnum.ACTIVITY_ERROR);

        activityService.delete(activityId);
        return ResultVOUtil.success();
    }

    /**
     * 更新活动信息
     * @param activityId 活动信息Id
     * @param activityForm 活动信息新表单
     * @return 更新的活动信息结果
     */
    @RequestMapping("/update")
    public ResultVO updateActivity(@RequestParam("activityId") String activityId,
                                   @ModelAttribute ActivityForm activityForm){
        ActivityInfo findRet = activityService.findOne(activityId);
        if(findRet == null)
            throw new BuckMooException(ResultEnum.ACTIVITY_ERROR);
        ActivityForm2ActivityInfo.activityForm2ActivityInfo(findRet, activityForm);
        log.info("【修改活动属性】activity={}", findRet);
        ActivityInfo save = activityService.save(findRet);
        return ResultVOUtil.success(save);
    }

    /**
     * 新建活动信息
     * @param activityForm 活动信息表单
     * @return 添加结果（如果成功则返回信息）
     */
    @RequestMapping("/add")
    public ResultVO addActivity(@ModelAttribute ActivityForm activityForm){
        ActivityInfo activityInfo = new ActivityInfo();
        ActivityForm2ActivityInfo.activityForm2ActivityInfo(activityInfo, activityForm);
        log.info("【修改活动属性】activity={}", activityInfo);
        activityInfo.setActivityId(KeyUtil.genUniqueKey());
        ActivityInfo save = activityService.save(activityInfo);
        return ResultVOUtil.success(save);
    }

    /**
     * 审核活动信息
     * @param activityId 活动Id
     * @param activityAudit 模板活动审核状态
     * @return 审核结果
     */
    @RequestMapping("/audit")
    public ResultVO audit(@RequestParam("activityId") String activityId,
                        @RequestParam("activityAudit") Integer activityAudit){
        ActivityInfo findRet = activityService.findOne(activityId);
        if(findRet == null) throw new BuckMooException(ResultEnum.ACTIVITY_ERROR);
        if(EnumUtil.getByCode(activityAudit, ActivityStatusEnum.class) == null){
            log.error("ActivityType={}", activityAudit);
            throw new BuckMooException(ResultEnum.PARAM_ERROR);
        }

        findRet.setActivityAudit(activityAudit);
        ActivityInfo saveAudit = activityService.save(findRet);
        assert saveAudit != null;
        return ResultVOUtil.success();
    }
}
