package edu.xpu.buckmoo.form;

import lombok.Data;

/**
 * 公司注册信息的表单
 */
@Data
public class CompanyForm {
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

}