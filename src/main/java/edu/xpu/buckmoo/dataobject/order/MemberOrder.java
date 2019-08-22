package edu.xpu.buckmoo.dataobject.order;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 公司成为会员支付的订单实体类
 */
@Data
@Entity
public class MemberOrder {
    /**
     * 订单Id对应通用订单Id
     */
    @Id
    private String orderId;

    /**
     * 支付成为会员公司的Id
     */
    private String orderCompany;

    /**
     * 支付金额
     */
    private BigDecimal orderMoney;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 支付的等级
     */
    private Integer memberLevel;
}
