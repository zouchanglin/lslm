package lishan.live.lslm.controller;

import lishan.live.lslm.convert.Activity2ActivityDTO;
import lishan.live.lslm.dto.ActivityInfoDTO;
import lishan.live.lslm.entity.ActivityInfo;
import lishan.live.lslm.enums.ResultEnum;
import lishan.live.lslm.exception.ActivityException;
import lishan.live.lslm.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ActivityController
 * @Description 活动信息管理的Controller
 * @Author tim
 * @Date 2019-04-19 20:45
 * @Version 1.0
 */

@Controller
@Slf4j
@RequestMapping(value = "/admin/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/show")
    public String showTableCompany(Map<String, Object> map,
        @RequestParam(value = "activityName", required = false) String activityName){
        List<ActivityInfoDTO> activities;
        if(activityName != null){
            activities = activityService.findAllActivityInfoDTONameLike(activityName);
        }else{
            activities = activityService.findAllActivityInfoDTO();
        }
        map.put("activities", activities);
        return "admin-activity";
    }

    @GetMapping("/update")
    public String updateActivityInfo(@RequestParam(value = "activityId")Integer activityId,
                                    Map<String, Object> map){
        ActivityInfoDTO activityInfoDTO = Activity2ActivityDTO.convert(activityService.findActivityInfoById(activityId));

        if(!activityId.equals(activityInfoDTO.getActivityId())){
            //如果公司信息不存在就抛出异常，因为这个是修改的接口
            throw new ActivityException(ResultEnum.ENTITY_NOT_EXIST);
        }
        map.put("activity", activityInfoDTO);
        return "activity/activity-update";
    }

    //TODO 修改
    @RequestMapping("/update-info")
    public String updateActivityInfo(@ModelAttribute ActivityInfoDTO activityInfo){
        if(activityInfo == null) return "error";
        log.info("【页面传回ActivityInfo】{}", activityInfo);
        //收到修改后的数据
        //ActivityInfo saveRet = activityService.saveActivityInfo(activityInfo);
        ActivityInfo saveRet = new ActivityInfo();
        return saveRet != null ? "success":"error";
    }

}
