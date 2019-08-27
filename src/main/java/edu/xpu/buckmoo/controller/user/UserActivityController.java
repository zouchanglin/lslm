package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.VO.ActivityInfoVO;
import edu.xpu.buckmoo.VO.ActivityVOStruct;
import edu.xpu.buckmoo.convert.ActivityInfo2VO;
import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.service.ActivityService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private ActivityInfo2VO activityInfo2VO;

    public UserActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * 展示正在进行的活动
     * @return 正在进行的活动列表
     */
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") Integer pageIndex,
                       @RequestParam(defaultValue = "4") Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<ActivityInfo> infoPage = activityService.findByActivityAudit(ActivityStatusEnum.PASS.getCode(), pageRequest);
        ActivityVOStruct activityVOStruct = new ActivityVOStruct();
        List<ActivityInfo> content = infoPage.getContent();
        List<ActivityInfoVO> activityInfoVOS = new ArrayList<>();
        for(ActivityInfo activityInfo: content){
            activityInfoVOS.add(activityInfo2VO.activityToVO(activityInfo));
        }
        activityVOStruct.setList(activityInfoVOS);
        activityVOStruct.setCurrentPage(pageIndex);
        activityVOStruct.setPageCount(infoPage.getTotalPages());
        return JsonUtil.toJson(ResultVOUtil.success(activityVOStruct));
    }
}
