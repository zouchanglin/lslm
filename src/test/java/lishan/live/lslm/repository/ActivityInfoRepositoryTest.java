package lishan.live.lslm.repository;

import lishan.live.lslm.entity.ActivityInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityInfoRepositoryTest {
    @Autowired
    private ActivityInfoRepository activityInfoRepository;

    @Test
    public void ActivityInfoRepositorySave(){
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityName("英雄联盟大赛");
        activityInfo.setActivitySponsor("123457");
        activityInfo.setActivityUndertake("123457");
        activityInfo.setActivityAddress("西安工程大学后门悠游网卡");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        try {
             date = sdf.parse("2020-2-10 8:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp date_sql = new Timestamp(date.getTime());
        activityInfo.setActivityStart(date_sql);
        activityInfo.setActivityEnd(date_sql);
        activityInfo.setActivityApply(date_sql);
        activityInfo.setActivityUnapply(date_sql);
        activityInfo.setActivityMaxpeople(300);
        activityInfo.setActivityMode(1);
        activityInfo.setActivityGeneralize(300);
        activityInfo.setActivityLink("www.youyou.com");
        activityInfo.setActivityAbstract("本次活动举办是为了喜欢打英雄联盟游戏的广大群众...");
        activityInfo.setActivityLogo("http://yyy/zzz.png");

        ActivityInfo save = activityInfoRepository.save(activityInfo);
        System.out.println(save);
        assertNotNull(save);
    }

    @Test
    public void ActivityInfoRepositoryFindByMode(){
        List<ActivityInfo> byActivityMode = activityInfoRepository.findByActivityMode(0);
        assertEquals(0, byActivityMode.size());
    }

    @Test
    public void  ActivityInfoRepositoryFindById(){
        Optional<ActivityInfo> byId = activityInfoRepository.findById(2);
        System.out.println(byId);
        assertNotNull(byId);
    }
}