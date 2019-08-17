package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.ActivityModeEnum;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author tim
 * @version 1.2
 * @className ActivityInfo
 * @description 活动信息实体类
 * @date 2019-08-17 23:23
 */
@Data
@Entity
@DynamicUpdate
public class ActivityInfo {
    @Id
    private String activityId;
    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 主办方Id
     */
    private String activityOpenid;

    /**
     * 活动模式：(0)企业组织的活动 (1)学生社团活动 (2) 其他
     */
    private Integer activityMode = ActivityModeEnum.COMPANY.getCode();

    /**
     * 如果是100那就代表此活动100人左右的推广力度，学生社团活动可不选择
     */
    private Integer activityGeneralize = 0;

    /**
     * 活动链接：一个跳转链接，跳转至活动页面
     */
    private String activityLink;

    /**
     * 活动简介，意义等等
     */
    private String activityAbstract;

    /**
     * 活动宣传图
     */
    private String activityLogo;

    /**
     * 活动审核 （0）未审核 （1）通过（2）未通过 (3)已经结束
     */
    private Integer activityAudit = ActivityStatusEnum.NEW.getCode();
}