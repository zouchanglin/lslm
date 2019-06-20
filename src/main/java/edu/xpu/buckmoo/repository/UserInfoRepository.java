package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户信息JPA接口
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    List<UserInfo> findAllByUserIdAndOpenId(String userId, String openId);
}
