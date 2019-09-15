package edu.xpu.buckmoo.form;

import edu.xpu.buckmoo.enums.ActivityModeEnum;
import lombok.Data;


/**
 * @author tim
 * @version 1.0
 * @className ActivityForm
 * @description 活动页面的表单
 * @date 2019-06-22 10:30
 */
@Data
public class ActivityForm {
    /**
     * 活动名称
     */
    private String activityName;

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
}
