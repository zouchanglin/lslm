package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className ActivityService
 * @description 活动信息的相关服务
 * @date 2019-06-20 21:09
 */
public interface ActivityService {
    /**
     * 查找一个活动信息实体
     * @param activityId 活动信息主键
     * @return 动信息实体
     */
    ActivityInfo findOne(String activityId);

    /**
     * 查找所有活动信息
     * @return 所有活动信息
     */
    List<ActivityInfo> findAll();

    /**
     * 根据状态查找所有活动信息
     * @param activityAudit 活动状态
     * @return 活动信息
     */
    Page<ActivityInfo> findByActivityAudit(Integer activityAudit, Pageable pageable);

    ActivityInfo save(ActivityInfo activityInfo);

    void delete(String activityId);

    ActivityInfo create(ActivityInfo activityInfo);
}
