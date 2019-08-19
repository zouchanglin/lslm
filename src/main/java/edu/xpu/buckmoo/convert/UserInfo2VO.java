package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.VO.UserInfoVO;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.enums.UserSexEnum;
import edu.xpu.buckmoo.utils.EnumUtil;
import org.springframework.beans.BeanUtils;

public class UserInfo2VO {
    public static UserInfoVO userInfoToUserInfoVO(UserInfo userInfo){
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        MemberLevelEnum memberEnum = EnumUtil.getByCode(userInfo.getUserMember(), MemberLevelEnum.class);
        if(memberEnum != null)
            userInfoVO.setUserMemberStr(memberEnum.getMessage());
        UserSexEnum userSexEnum = EnumUtil.getByCode(userInfo.getUserSex(), UserSexEnum.class);
        if(userSexEnum != null)
            userInfoVO.setUserSexStr(userSexEnum.getMessage());
        return userInfoVO;
    }
}
