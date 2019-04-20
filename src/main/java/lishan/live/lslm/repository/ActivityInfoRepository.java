package lishan.live.lslm.repository;

import lishan.live.lslm.entity.ActivityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ActivityInfoRepository
 * @Description 操作活动信息ActivityInfo表的类
 * @Author tim
 * @Date 2019-04-14 20:23
 * @Version 1.0
 */
public interface ActivityInfoRepository extends JpaRepository<ActivityInfo, Integer> {
    /**
     * 根据活动模式查找符合模式的活动
     * @param activityMode 活动模式
     * @return 符合模式的活动List
     */
    List<ActivityInfo> findByActivityMode(Integer activityMode);

    List<ActivityInfo> findByActivityNameLike(String activityName);
}
