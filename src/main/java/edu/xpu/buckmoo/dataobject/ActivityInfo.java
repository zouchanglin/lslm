package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.ActivityModeEnum;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author tim
 * @version 1.0
 * @className ActivityInfo
 * @description 活动信息实体类
 * @date 2019-06-10 23:23
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
    private String activityMain;

    /**
     * 协办方Id
     */
    private String activityUnmain;

    /**
     * 活动地点
     */
    private String activityAddress;

    /**
     * 开始时间
     */
    private Date activityStart;
    /**
     * 结束时间
     */
    private Date activityEnd;

    /**
     * 最大人数
     */
    private Integer activityMax;

    /**
     * 活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他
     */
    private Integer activityMode = ActivityModeEnum.COMPANY.getCode();

    /**
     * 如果是100那就代表此活动100人左右的推广力度，学生社团活动可不选择
     */
    private Integer activityGeneralize;

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

    /**
     * 此信息最后修改时间
     */
    private Date activityUpdate;

    /**
     * 信息新建时间
     */
    private Date activityCreate;
}
