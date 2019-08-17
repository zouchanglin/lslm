package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.form.CompanyForm;
import org.springframework.beans.BeanUtils;

public class CompanyForm2CompanyInfo {
    public static CompanyInfo form2info(CompanyForm companyForm){
        CompanyInfo companyInfo = new CompanyInfo();
        BeanUtils.copyProperties(companyForm, companyInfo);
        //companyInfo.setCompanyRegTime(System.currentTimeMillis());
        //companyInfo.setCompanyUpdateTime(System.currentTimeMillis());
        companyInfo.setCompanyStatus(CompanyStatusEnum.NEW.getCode());
        companyInfo.setCompanyMember(0);
        return companyInfo;
    }
}
