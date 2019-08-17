package edu.xpu.buckmoo.dataobject.order;

import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 公司发布活动的订单实体类
 */
@Data
@Entity
@DynamicUpdate
public class CompanyOrder {
    @Id
    private String orderId;

    /**
     * 支付金额
     */
    private BigDecimal orderMoney;

    /**
     * 要发布的活动Id
     */
    private String orderActivity;

    /**
     * 要发布的活动对应状态
     */
    private Integer activityStatus = ActivityStatusEnum.NEW.getCode();

    /**
     * 信息创建时间
     */
    private Long createTime;

    /**
     * 信息最后更新时间
     */
    private Long updateTime;
}