package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.enums.ActivityModeEnum;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ActivityInfoRepositoryTest {
    @Autowired
    private ActivityInfoRepository activityInfoRepository;
    @Test
    public void save() {
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityName("活动名称3");
        activityInfo.setActivityId("15660199930331126194");
        activityInfo.setActivityAbstract("活动描述信息");
        activityInfo.setActivityGeneralize(1000);
        activityInfo.setActivityMode(ActivityModeEnum.COMPANY.getCode());
        activityInfo.setActivityLink("http://xxx");
        activityInfo.setActivityLogo("http://a.png");
        activityInfo.setActivityAudit(ActivityStatusEnum.NEW.getCode());
        activityInfo.setActivityOpenid(KeyUtil.genUniqueKey());

        ActivityInfo save = activityInfoRepository.save(activityInfo);
        assertNotNull(save);
        log.info("[ActivityInfoRepositoryTest] save={}", save);
    }
}