package edu.xpu.buckmoo.form;

import edu.xpu.buckmoo.enums.SexEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 兼职信息的表单
 */
@Data
public class PartTimeForm {
    /**
     * 兼职名称
     */
    private String partName;

    /**
     * 兼职类型
     */
    private Integer partCategory;

    /**
     * 兼职地点
     */
    private String partAddress;

    /**
     * 兼职详细描述
     */
    private String partOverview;

    /**
     * 兼职开始时间
     */
    private Long partStart;

    /**
     * 兼职结束时间
     */
    private Long partEnd;

    /**
     * 对兼职时间的一个补充
     */
    private String partTime;

    /**
     * 发起方支付金额
     */
    private BigDecimal partMoney = new BigDecimal(0);

    /**
     * 任务接受者要求：男、女、男女不限
     */
    private Integer employSex = SexEnum.OTHER.getCode();

    /**
     * 兼职信息发布者的联系方式
     */
    private String creatorPhone;
}
