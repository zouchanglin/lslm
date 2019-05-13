package lishan.live.lslm.service;

import lishan.live.lslm.entity.UserInfo;

import java.util.List;

/**
 * @ClassName UserService
 * @Description
 * @Author tim
 * @Date 2019-04-29 19:24
 * @Version 1.0
 */
public interface UserService {
    List<UserInfo> findAll();
    List<UserInfo> findAllByNameLike(String userName);
    UserInfo findUserInfoById(String userId);
    void deleteUserInfo(String openId);

    UserInfo saveUserInfo(UserInfo userInfo);
}
