package edu.xpu.buckmoo.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@DynamicUpdate
public class ProblemFeedback {
    @Id
    private String problemId;
    private String problemContent;
    private Integer problemDealwith = 0;
    private String contactWay;
}