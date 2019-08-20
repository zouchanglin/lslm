package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /**
     * 保存一个活动信息
     * @param activityInfo 活动信息
     * @return 保存之后的信息
     */
    ActivityInfo save(ActivityInfo activityInfo);

    /**
     * 删除一个活动信息
     * @param activityId 活动信息ID
     */
    void delete(String activityId);

    /**
     * 创建一个活动信息
     * @param activityInfo 活动信息实体
     * @return 保存后的活动信息
     */
    ActivityInfo create(ActivityInfo activityInfo);

    /**
     * 展示企业发布的所有活动
     * @param openid 企业管理员openid
     * @param pageRequest 分页查询参数
     * @return 分页查询结果
     */
    Page<ActivityInfo> myAllActivity(String openid, PageRequest pageRequest);
}
