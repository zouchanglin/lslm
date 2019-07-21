package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.repository.UserInfoRepository;
import edu.xpu.buckmoo.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author tim
 * @version 1.0
 * @className UserInfoServiceImpl
 * @description
 * @date 2019-06-21 21:51
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userRep;

    public UserInfoServiceImpl(UserInfoRepository userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userRep.save(userInfo);
    }

    @Override
    public UserInfo findById(String openId) {
        return userRep.findById(openId).orElse(null);
    }
}
