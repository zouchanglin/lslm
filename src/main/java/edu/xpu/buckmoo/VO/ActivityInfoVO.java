package edu.xpu.buckmoo.VO;

import edu.xpu.buckmoo.enums.ActivityModeEnum;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author tim
 * @version 1.2
 * @className ActivityInfoVO
 * @description
 * @date 2019-06-20 22:12
 */
@Data
public class ActivityInfoVO {
    @Id
    private String activityId;
    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 主办方Id（这个其实是企业管理员的openid）
     */
    private String activityOpenid;

    /**
     * 企业名称（企业管理员的openid对应的企业名称）
     */
    private String activityOpenidStr;

    /**
     * 活动模式：(0)企业组织的活动 (1)学生社团活动 (2) 其他
     */
    private Integer activityMode = ActivityModeEnum.COMPANY.getCode();

    /**
     * 活动模式
     */
    private String activityModeStr;

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


    private String activityAuditStr;
}
