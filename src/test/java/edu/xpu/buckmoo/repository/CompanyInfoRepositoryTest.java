package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CompanyInfoRepositoryTest {
    @Autowired
    private CompanyInfoRepository repository;

    @Test
    public void save(){
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyId(KeyUtil.genUniqueKey());
        companyInfo.setCompanyName("比特科技");
        companyInfo.setCompanyLogo("http://logo.png");
        companyInfo.setCompanyDescribe("培训C/C++高级工程师，Java工程师，Python工程师");
        companyInfo.setCompanyLicense("http://license.png");
        companyInfo.setCompanyManger("张鹏伟");
        companyInfo.setCompanyPhone("13724344782");
        companyInfo.setCompanyIdCard("612328198008083417");
        companyInfo.setCompanyRegTime(new Date());
        companyInfo.setCompanyUnRegTime(new Date());
        companyInfo.setCompanyStatus(CompanyStatusEnum.NEW.getCode());
        companyInfo.setCompanyGrade(0);
        CompanyInfo saveRet = repository.save(companyInfo);
        assertNotNull(saveRet);
    }

    @Test
    public void findByOpenId(){
        CompanyInfo byOpenId = repository.findByOpenId("123");
        log.info("CompanyInfo={}", byOpenId);
        assertNotNull(byOpenId);
    }
}