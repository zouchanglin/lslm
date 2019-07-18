package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.enums.SexEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tim
 * @version 1.0
 * @className PartInfo
 * @description 兼职信息实体类
 * @date 2019-06-11 18:03
 */
@Data
@Entity
@DynamicUpdate
public class PartInfo {
    @Id
    private String partId;

    /**
     * 兼职名称
     */
    private String partName;

    /**
     * 兼职类型
     */
    private Integer partCategory;


    /**
     * 兼职地点
     */
    private String partAddress;

    /**
     * 兼职详细描述
     */
    private String partOverview;

    /**
     * 兼职开始时间
     */
    private Date partStart;

    /**
     * 兼职结束时间
     */
    private Date partEnd;

    /**
     * 对兼职时间的一个补充
     */
    private String partTime;

    /**
     * 兼职发布者
     */
    private String partCreator;

    /**
     * 发起方支付金额
     */
    private BigDecimal partMoney = new BigDecimal(0);

    /**
     * 此兼职信息的状态
     */
    private Integer partStatus = PartTimeStatusEnum.NEW_PART.getCode();

    /**
     * 兼职任务接受者UserId
     */
    private String partEmploy;

    /**
     * 任务接受者要求：男、女、男女不限
     */
    private Integer employSex = SexEnum.OTHER.getCode();

    /**
     * 兼职的备注信息
     */
    private String partRemark;

    /**
     * 兼职信息发布者的联系方式
     */
    private String creatorPhone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 信息更新时间
     */
    private Date updateTime;
}
