package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CommunityInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class CommunityRepositoryTest {
    @Autowired
    private CommunityRepository communityRepository;

    @Test
    public void findFirstByOpenid() {
        CommunityInfo communityInfo = new CommunityInfo();

        communityInfo.setOpenid("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
        communityInfo.setMember(0);
        communityInfo.setName("1");
        communityInfo.setSchool("1");
        communityInfo.setDes("1");
        communityInfo.setIcon("1");

        CommunityInfo save = communityRepository.save(communityInfo);
        log.info("[CommunityRepository] saveRes={}", save);
        assertNotNull(save);
    }
}
