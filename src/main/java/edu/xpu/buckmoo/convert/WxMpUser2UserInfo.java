package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.dataobject.UserInfo;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author tim
 * @version 1.0
 * @className WxMpUser2UserInfo
 * @description
 * @date 2019-06-21 21:58
 */
public class WxMpUser2UserInfo {
    public static UserInfo WechatMpUser2UserInfo(WxMpUser wxMpUser){
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(wxMpUser.getOpenId());
        userInfo.setUserName(wxMpUser.getNickname());
        userInfo.setUserIcon(wxMpUser.getHeadImgUrl());
        userInfo.setUserSex(wxMpUser.getSex());
        userInfo.setUserCity(wxMpUser.getCity());
        return userInfo;
    }
}
