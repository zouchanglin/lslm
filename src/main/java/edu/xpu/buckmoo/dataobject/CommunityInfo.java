package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class CommunityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String school;

    private String icon;

    private String des;

    private String openid;

    private Integer member;

    private Integer status = CompanyStatusEnum.NEW.getCode();
}
