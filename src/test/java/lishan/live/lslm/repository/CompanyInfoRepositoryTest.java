package lishan.live.lslm.repository;

import lishan.live.lslm.entity.CompanyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyInfoRepositoryTest {
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Test
    public void CompanyInfoRepositoryFindLikeName(){
        List<CompanyInfo> list = companyInfoRepository.findByCompanyNameLike("%科技%");
        assertEquals(6, list.size());
    }


    @Test
    public void CompanyInfoRepositorySave(){
        for (int i = 0; i < 15; i++) {
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setCompanyId("123458"+i);
            companyInfo.setCompanyName("商汤科技");
            companyInfo.setCompanyLogo("https://getbootstrap.com/docs/4.3/assets/img/bootstrap-themes.png");
            companyInfo.setCompanyDescribe("致力于打造服务于各商业领域的AIoT操作系统，视觉图像处理2");
            companyInfo.setCompanyLicense("https://v3.bootcss.com/assets/img/devices.png");
            companyInfo.setCompanyRegister("西安市临潼区工商管理局");
            companyInfo.setCompanyManger("AAA");
            companyInfo.setCompanyPhone("15372809183");
            companyInfo.setCompanyIdcard("612337193012204519");
            Date date = new Date(System.currentTimeMillis());
            companyInfo.setCompanyRegdate(new Date(date.getTime()));
            //一天的毫秒值
            long ONE_DAY_MILLISECONDS = 60 * 60 * 24 * 1000;
            companyInfo.setCompanyUnregdate(new Date(date.getTime() + ONE_DAY_MILLISECONDS*365));
            companyInfo.setCompanyStatus(1);
            CompanyInfo save = companyInfoRepository.save(companyInfo);
            assertNotNull(save);
        }
    }

    @Test
    public void CompanyInfoRepositoryFindByStatus(){
        List<CompanyInfo> byCompanyStatus = companyInfoRepository.findByCompanyStatus(2);
        assertEquals(1, byCompanyStatus.size());
    }


    @Test
    public void CompanyInfoRepositoryFindOne(){
        Optional<CompanyInfo> byId = companyInfoRepository.findById("123456");
        System.out.println(byId);
        assertNotNull(byId);
    }
}