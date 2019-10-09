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

        for (int i = 0; i < 500; i++) {
            ActivityInfo activityInfo = new ActivityInfo();
            activityInfo.setActivityName("活动名称" + i);
            activityInfo.setActivityId(KeyUtil.genUniqueKey());
            activityInfo.setActivityAbstract("活动描述信息" + i);
            activityInfo.setActivityGeneralize(i + 1000);
            if(i% 3 != 0){
                activityInfo.setActivityMode(ActivityModeEnum.COMPANY.getCode());
            }else{
                activityInfo.setActivityMode(ActivityModeEnum.STUDENT.getCode());
            }

            activityInfo.setActivityLink("http://xxx.xxx.xxx.xxx." + i);
            activityInfo.setActivityLogo("http://xxx.xxx.xxx" + i + ".png");
            activityInfo.setActivityAudit(ActivityStatusEnum.PASS.getCode());
            if(i% 2 != 0){
                activityInfo.setActivityOpenid("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
            }else{
                activityInfo.setActivityOpenid("oxrwq0xrKKyqiAGE8O9TM3L1yaQY");
            }

            ActivityInfo save = activityInfoRepository.save(activityInfo);
            assertNotNull(save);
            log.info("[ActivityInfoRepositoryTest] save={}", save);
        }

    }
}