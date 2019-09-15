package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.UserInfo;

/**
 * @author tim
 * @version 1.0
 * @className UserInfoService
 * @description
 * @date 2019-06-21 21:50
 */
public interface UserInfoService {
    UserInfo saveUser(UserInfo userInfo);

    UserInfo findById(String openId);

    Integer userCount();
}
