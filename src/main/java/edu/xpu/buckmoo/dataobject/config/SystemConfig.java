package edu.xpu.buckmoo.dataobject.config;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class SystemConfig {
    @Id
    private String paramsId;
    private BigDecimal paramsValue;
}