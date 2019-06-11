package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author tim
 * @version 1.0
 * @className CompanyInfo
 * @description 公司信息实体类
 * @date 2019-06-10 23:23
 */
@Data
@Entity
@DynamicUpdate
public class CompanyInfo {
    /**
     * 社会统一信用码
     */
    @Id
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司Logo路径
     */
    private String companyLogo;

    /**
     * 公司经营描述
     */
    private String companyDescribe;

    /**
     * 公司营业执照
     */
    private String companyLicense;

    /**
     * 公司法人姓名
     */
    private String companyManger;

    /**
     * 公司法人联系方式
     */
    private String companyPhone;

    /**
     * 公司法人身份证号
     */
    private String companyIdCard;

    /**
     * 公司注册时间
     */
    private Date companyRegTime;

    /**
     * 公司到期时间
     */
    private Date companyUnRegTime;

    /**
     * 信息更新时间
     */
    private Date companyUpdateTime;

    /**
     * 公司审核情况
     */
    private Integer companyStatus = CompanyStatusEnum.NEW.getCode();

    /**
     * 公司积分/信誉值
     */
    private Integer companyGrade;
}
