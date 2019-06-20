package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.repository.ActivityInfoRepository;
import edu.xpu.buckmoo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className ActivityServiceImpl
 * @description 活动信息
 * @date 2019-06-20 21:13
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityInfoRepository activityRep;
    @Override
    public ActivityInfo findOne(String activityId) {
        return activityRep.findById(activityId).orElse(null);
    }

    @Override
    public List<ActivityInfo> findAll() {
        return activityRep.findAll();
    }

    @Override
    public List<ActivityInfo> findByActivityAudit(Integer activityAudit) {
        return activityRep.findAllByActivityAudit(activityAudit);
    }

    @Override
    public ActivityInfo save(ActivityInfo activityInfo) {
        return activityRep.save(activityInfo);
    }
}
