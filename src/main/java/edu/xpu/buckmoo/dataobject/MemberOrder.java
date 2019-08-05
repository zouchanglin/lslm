package edu.xpu.buckmoo.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class MemberOrder {
    @Id
    private String orderId;

    private String orderCompany;

    private BigDecimal orderMoney;

    private Integer payStatus;

    private String openid;
}
