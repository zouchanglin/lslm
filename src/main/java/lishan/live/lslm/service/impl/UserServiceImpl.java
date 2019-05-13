package lishan.live.lslm.service.impl;

import lishan.live.lslm.entity.UserInfo;
import lishan.live.lslm.repository.UserInfoRepository;
import lishan.live.lslm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author tim
 * @Date 2019-04-29 19:25
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public List<UserInfo> findAllByNameLike(String userName) {
        return userInfoRepository.findByUserNameLike("%"+userName+"%");
    }

    @Override
    public UserInfo findUserInfoById(String userId) {
        return userInfoRepository.findById(userId).orElse(new UserInfo());
    }

    @Override
    public void deleteUserInfo(String openId) {
        userInfoRepository.deleteById(openId);
    }

    @Override
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }
}
