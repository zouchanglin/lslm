package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.enums.ActivityModeEnum;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityInfoRepositoryTest {
    @Autowired
    private ActivityInfoRepository repository;
    @Test
    public void save(){
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityId(KeyUtil.genUniqueKey());
        activityInfo.setActivityName("钟南山一日游");
        activityInfo.setActivityMain(KeyUtil.genUniqueKey());
        activityInfo.setActivityAddress("终南山");
        activityInfo.setActivityStart(System.currentTimeMillis());
        activityInfo.setActivityEnd(System.currentTimeMillis());
        activityInfo.setActivityMax(50);
        activityInfo.setActivityGeneralize(2000);
        activityInfo.setActivityLink("http://lslm.jeck");
        activityInfo.setActivityAbstract("欣赏终南山美景，品尝终南山的美食！");
        activityInfo.setActivityLogo("http://lslm.png");
        activityInfo.setActivityAudit(ActivityStatusEnum.NEW.getCode());
        activityInfo.setActivityMode(ActivityModeEnum.COMPANY.getCode());
        ActivityInfo saveRet = repository.save(activityInfo);
        assertNotNull(saveRet);
    }


    @Test
    public void find(){
        PageRequest request = PageRequest.of(0,3);
        Page<ActivityInfo> page = repository.findAllByActivityAudit(ActivityStatusEnum.PASS.getCode(), request);
        assertNotEquals(0, page.getTotalElements());
    }
}