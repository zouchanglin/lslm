package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.VO.UserInfoVO;
import edu.xpu.buckmoo.dataobject.CommunityInfo;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.enums.UserSexEnum;
import edu.xpu.buckmoo.service.CommunityService;
import edu.xpu.buckmoo.utils.EnumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserInfo2VO {
    private final CommunityService communityService;

    public UserInfo2VO(CommunityService communityService) {
        this.communityService = communityService;
    }

    public UserInfoVO userInfoToUserInfoVO(UserInfo userInfo){
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        MemberLevelEnum memberEnum = EnumUtil.getByCode(userInfo.getUserMember(), MemberLevelEnum.class);
        if(memberEnum != null)
            userInfoVO.setUserMemberStr(memberEnum.getMessage());
        UserSexEnum userSexEnum = EnumUtil.getByCode(userInfo.getUserSex(), UserSexEnum.class);
        if(userSexEnum != null)
            userInfoVO.setUserSexStr(userSexEnum.getMessage());
        //判断是否是社团
        CommunityInfo communityInfo = communityService.findByOpenid(userInfo.getOpenId());
        if(communityInfo != null)
            userInfoVO.setCommunityId(communityInfo.getId());
        return userInfoVO;
    }
}
