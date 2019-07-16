package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.CompanyOrderEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class CompanyOrder {
    @Id
    private String orderId;

    /**
     * 订单支付状态
     */
    private Integer orderStatus = CompanyOrderEnum.NOT_PAY.getCode();

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
    private Integer activityStatus;

    private Date createTime;

    private Date updateTime;
}
