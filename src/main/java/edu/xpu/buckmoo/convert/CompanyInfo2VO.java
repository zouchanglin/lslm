package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.VO.CompanyInfoVO;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.utils.EnumUtil;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CompanyInfo2VO {
    public static CompanyInfoVO companyInfoToVO(CompanyInfo companyInfo){
        CompanyInfoVO companyInfoVO = new CompanyInfoVO();
        BeanUtils.copyProperties(companyInfo, companyInfoVO);

        CompanyStatusEnum statusEnum = EnumUtil.getByCode(companyInfo.getCompanyStatus(), CompanyStatusEnum.class);
        assert statusEnum != null;
        companyInfoVO.setCompanyStatusStr(statusEnum.getMessage());

        MemberLevelEnum memberLevelEnum = EnumUtil.getByCode(companyInfo.getCompanyMember(), MemberLevelEnum.class);
        assert memberLevelEnum != null;
        companyInfoVO.setCompanyMemberStr(memberLevelEnum.getMessage());

        Long memberOverdue = companyInfo.getMemberOverdue();
        if(memberOverdue != null && memberOverdue > 0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            companyInfoVO.setMemberOverdueStr(dateFormat.format(new Date(companyInfo.getMemberOverdue())));
        }else{
            companyInfoVO.setMemberOverdue(0L);
            companyInfoVO.setMemberOverdueStr("未注册会员");
        }
        return companyInfoVO;
    }
}
