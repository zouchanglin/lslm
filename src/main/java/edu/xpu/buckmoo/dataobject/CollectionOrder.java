package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.CollectionOrderTypeEnum;
import edu.xpu.buckmoo.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class CollectionOrder {

    @Id
    private String orderId;

    /**
     * 订单类型
     */
    private Integer orderType = CollectionOrderTypeEnum.OTHER_PAY.getCode();

    /**
     * 订单金额
     */
    private BigDecimal orderMoney;

    /**
     * 支付者openid
     */
    private String orderOpenid;

    /**
     * 订单的名称
     */
    private String orderName;

    /**
     * 支付状态
     */
    private Integer orderPayStatus = PayStatusEnum.NOT_PAY.getCode();
}
