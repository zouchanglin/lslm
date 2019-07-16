package edu.xpu.buckmoo.form;

import lombok.Data;

import java.math.BigDecimal;

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
     * 兼职的备注信息
     */
    private String partRemark;
}
