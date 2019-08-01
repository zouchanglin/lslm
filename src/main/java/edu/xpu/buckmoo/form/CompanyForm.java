package edu.xpu.buckmoo.form;

import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import lombok.Data;

/**
 * 公司注册信息的表单
 */
@Data
public class CompanyForm {
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
     * 公司审核情况
     */
    private Integer companyStatus = CompanyStatusEnum.NEW.getCode();

    /**
     * 公司积分/信誉值
     */
    private Integer companyGrade = 0;

    /**
     * 管理员OpenId
     */
    private String openId;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 会员等级
     */
    private Integer companyMember = MemberLevelEnum.COMMON.getCode();
}
