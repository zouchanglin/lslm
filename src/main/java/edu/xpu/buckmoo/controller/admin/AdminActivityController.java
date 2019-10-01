package edu.xpu.buckmoo.controller.admin;

import edu.xpu.buckmoo.VO.ActivityInfoVO;
import edu.xpu.buckmoo.VO.ActivityVOStruct;
import edu.xpu.buckmoo.convert.ActivityInfo2VO;
import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.enums.ErrorResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.service.ActivityService;
import edu.xpu.buckmoo.utils.EnumUtil;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tim
 * @version 1.2
 * @className ActivityController
 * @description 活动管理模块
 * @date 2019-08-24 19:08
 */
@RestController
@RequestMapping("/admin/activity")
@Slf4j
public class AdminActivityController {
    private final ActivityService activityService;

    private final ActivityInfo2VO activityInfo2VO;

    public AdminActivityController(ActivityService activityService, ActivityInfo2VO activityInfo2VO) {
        this.activityService = activityService;
        this.activityInfo2VO = activityInfo2VO;
    }

    /**
     * 管理员查看活动
     * @return 活动列表
     */
    @GetMapping("/show")
    public String showActivityInfo(@RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   HttpSession httpSession){
        if(SessionOpen.openSession){
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if(BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin")) return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }


        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<ActivityInfo> activityInfoPage = activityService.findAllByPage(pageRequest);
        List<ActivityInfo> content = activityInfoPage.getContent();

        ActivityVOStruct activityVOStruct = new ActivityVOStruct();
        List<ActivityInfoVO> list = new ArrayList<>();
        for(ActivityInfo activityInfo: content){
            list.add(activityInfo2VO.activityToVO(activityInfo));
        }
        activityVOStruct.setList(list);
        activityVOStruct.setPageCount(activityInfoPage.getTotalPages());
        activityVOStruct.setCurrentPage(pageIndex);
        activityVOStruct.setCount(content.size());


        return JsonUtil.toJson(ResultVOUtil.success(activityVOStruct));
    }


    /**
     * 审核活动信息
     * @param activityId 活动Id
     * @param activityAudit 模板活动审核状态
     * @return 审核结果
     */
    @GetMapping("/audit")
    public String audit(@RequestParam("activityId") String activityId,
                        @RequestParam("activityAudit") Integer activityAudit,
                        HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        ActivityInfo findRet = activityService.findOne(activityId);
        if(findRet == null) throw new BuckMooException(ErrorResultEnum.ACTIVITY_ERROR);
        if(EnumUtil.getByCode(activityAudit, ActivityStatusEnum.class) == null){
            log.error("ActivityType={}", activityAudit);
            return JsonUtil.toJson(ResultVOUtil.error(1, "参数错误"));
        }


        findRet.setActivityAudit(activityAudit);
        ActivityInfo saveAudit = activityService.save(findRet);
        return JsonUtil.toJson(ResultVOUtil.success(activityInfo2VO.activityToVO(saveAudit)));
    }
}
