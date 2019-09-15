package edu.xpu.buckmoo.VO;

import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import lombok.Data;


@Data
public class CompanyInfoVO {
    /**
     * 社会统一信用码
     */
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司营业执照
     */
    private String companyLicense;

    /**
     * 公司法人联系方式
     */
    private String companyPhone;

    /**
     * 公司审核情况
     */
    private Integer companyStatus = CompanyStatusEnum.NEW.getCode();

    /**
     * 审核情况
     */
    private String companyStatusStr;

    /**
     * 管理员openid
     */
    private String openid;

    /**
     * 会员等级
     */
    private Integer companyMember = MemberLevelEnum.COMMON.getCode();
    /**
     * 会员等级
     */
    private String companyMemberStr;

    /**
     * 公司会员到期时间
     */
    private Long memberOverdue;
    /**
     * 会员到期时间
     */
    private String memberOverdueStr;
}