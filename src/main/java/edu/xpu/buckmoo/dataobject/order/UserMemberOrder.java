package edu.xpu.buckmoo.dataobject.order;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@DynamicUpdate
@Data
@Entity
public class UserMemberOrder {
    @Id
    private String orderId;
    private String orderCollection;
    private Integer memberType;
    private String openid;
}
