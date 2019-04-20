package lishan.live.lslm.entity;

import lishan.live.lslm.enums.CompanyStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class CompanyInfo {

    /**
     * 公司社会统一信用码
     */
    @Id
    private String companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业Logo的图片路径
     */
    private String companyLogo;

    /**
     * 企业的描述，经营范围之类的
     */
    private String companyDescribe;

    /**
     * 企业营业执照图片路径
     */
    private String companyLicense;

    /**
     * 注册机关地址，比如：北京市工商行政管理局海淀分局'
     */
    private String companyRegister;

    /**
     * 企业法人姓名
     */
    private String companyManger;

    /**
     * 企业法人电话
     */
    private String companyPhone;

    /**
     * 企业法人身份证号码
     */
    private String companyIdcard;

    /**
     * 企业在此平台起始注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date companyRegdate;

    /**
     * 企业在此平台注册到期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date companyUnregdate;

    /**
     * 企业信息最后修改更新时间
     */


    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date companyUpdatedate;

    /**
     * 企业注册状态 0、未审核 1、通过 2、未通过（暂时设置了3种状态）
     */
    private Integer companyStatus = CompanyStatusEnum.UN_AUDITED.getCode();
}
