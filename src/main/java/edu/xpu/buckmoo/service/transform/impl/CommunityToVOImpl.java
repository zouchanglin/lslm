package edu.xpu.buckmoo.service.transform.impl;

import edu.xpu.buckmoo.VO.CommunityInfoVO;
import edu.xpu.buckmoo.dataobject.CommunityInfo;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.service.transform.CommunityToVO;
import edu.xpu.buckmoo.utils.EnumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CommunityToVOImpl implements CommunityToVO {

    @Override
    public CommunityInfoVO communityToVO(CommunityInfo communityInfo) {
        CommunityInfoVO communityInfoVO = new CommunityInfoVO();

        BeanUtils.copyProperties(communityInfo, communityInfoVO);
        MemberLevelEnum levelEnum = EnumUtil.getByCode(communityInfo.getMember(), MemberLevelEnum.class);
        CompanyStatusEnum companyStatusEnum = EnumUtil.getByCode(communityInfo.getStatus(), CompanyStatusEnum.class);
        if(levelEnum != null && companyStatusEnum != null){
            communityInfoVO.setMemberStr(levelEnum.getMessage());
            communityInfoVO.setStatusStr(companyStatusEnum.getMessage());
        }else{
            throw new RuntimeException("状态（等级）信息错误");
        }
        return communityInfoVO;
    }
}
