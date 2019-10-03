package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CommunityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityInfo, Integer> {
    CommunityInfo findFirstByOpenid(String openid);
}
