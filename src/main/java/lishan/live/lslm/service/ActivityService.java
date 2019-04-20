package lishan.live.lslm.service;

import lishan.live.lslm.entity.ActivityInfo;
import lishan.live.lslm.entity.CompanyInfo;

import java.util.List;

/**
 * @ClassName CompanyService
 * @Description
 * @Author tim
 * @Date 2019-04-18 17:53
 * @Version 1.0
 */

public interface ActivityService {
    ActivityInfo findActivityInfoById(Integer activityId);

    ActivityInfo saveActivityInfo(ActivityInfo activityInfo);

    List<ActivityInfo> findAllActivityInfo();

    //活动名称相似匹配查询
    List<ActivityInfo> findAllActivityInfoNameLike(String activityName);

    void deleteActivityInfo(Integer activityId);
}
