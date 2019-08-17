package edu.xpu.buckmoo.dataobject.config;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Data
@Entity
/*
 * 系统参数配置
 */
public class SystemConfig {
    @Id
    private String paramsId;

    /**
     * 参数值
     */
    private BigDecimal paramsValue;

    /**
     * 参数说明
     */
    private String paramsDes;
}