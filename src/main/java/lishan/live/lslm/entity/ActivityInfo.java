package lishan.live.lslm.entity;

import lishan.live.lslm.enums.ActivityStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName ActivityInfo
 * @Description 活动描述实体类
 * @Author tim
 * @Date 2019-04-14 19:42
 * @Version 1.0
 */
@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ActivityInfo {

    /**
     * 活动的主键，GenerationType.IDENTITY就是自动增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 主办方公司Id#主办方公司Id
     */
    private String activitySponsor;

    /**
     * 承办方或者协办方Id#承办方或者协办方Id
     */
    private String activityUndertake;

    /**
     * 活动地点
     */
    private String activityAddress;

    /*
     * 活动开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityStart;

    /**
     * 活动结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date activityEnd;

    /**
     * 报名开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityApply;

    /**
     * 结束报名时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityUnapply;

    /**
     * 活动允许的最大参加人数
     */
    private Integer activityMaxpeople;

    /**
     * 活动模式：(0)学生社团活动  (1)企业组织的活动  (2) 其他
     */
    private Integer activityMode;

    /**
     * 数字就代表人数，如果是100那就代表此活动100人左右的推广力度，学生社团活动可不选择
     */
    private Integer activityGeneralize;

    /**
     * 活动链接
     */
    private String activityLink;

    /**
     * 活动简述
     */
    private String activityAbstract;

    /**
     * 活动Logo
     */
    private String activityLogo;


    /**
     * 活动审核状态：活动审核 （0）未审核 （1）未通过（2）通过
     */
    private Integer activityAudit = ActivityStatusEnum.UN_AUDITED.getCode();


    /**
     * 最后修改时间
     */
    @LastModifiedDate
    private Date activityUpdate;
}
