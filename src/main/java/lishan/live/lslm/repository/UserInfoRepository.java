package lishan.live.lslm.repository;

import lishan.live.lslm.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName UserInfoRepository
 * @Description
 * @Author tim
 * @Date 2019-04-29 19:08
 * @Version 1.0
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    List<UserInfo> findByUserNameLike(String userName);
}
