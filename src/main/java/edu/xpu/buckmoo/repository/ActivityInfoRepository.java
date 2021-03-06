package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 活动信息JPA接口
 */
public interface ActivityInfoRepository extends JpaRepository<ActivityInfo, String> {
    Page<ActivityInfo> findAllByActivityAudit(Integer activityAudit, Pageable pageable);

    Page<ActivityInfo> findAllByActivityOpenid(String openid, Pageable pageable);
}
