package edu.xpu.buckmoo.dataobject.order;

import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.enums.SexEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author tim
 * @version 1.2
 * @className PartInfo
 * @description 兼职信息实体类
 * @date 2019-08-17 11:03
 */
@Data
@Entity
public class PartInfo {
    @Id
    private String partId;

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
     * 对兼职时间的一个补充(备注)
     */
    private String partTime;

    /**
     * 发起方支付金额
     */
    private BigDecimal partMoney = new BigDecimal(0);

    /**
     * 接收方接收金额
     */
    private BigDecimal partMoneyShow = new BigDecimal(0);

    /**
     * 此兼职信息的状态
     */
    private Integer partStatus = PartTimeStatusEnum.NO_PAY.getCode();

    /**
     * 任务接受者要求：男、女、男女不限
     */
    private Integer employSex = SexEnum.OTHER.getCode();

    /**
     * 兼职任务接受者openid
     */
    private String partEmploy;

    /**
     * 兼职任务接受者Phone
     */
    private String employPhone;

    /**
     * 兼职发布者openid
     */
    private String partCreator;
}
