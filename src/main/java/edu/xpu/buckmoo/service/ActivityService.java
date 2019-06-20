package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.ActivityInfo;

import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className ActivityService
 * @description
 * @date 2019-06-20 21:09
 */
public interface ActivityService {
    ActivityInfo findOne(String activityId);

    List<ActivityInfo> findAll();

    List<ActivityInfo> findByActivityAudit(Integer activityAudit);

    ActivityInfo save(ActivityInfo activityInfo);
}
