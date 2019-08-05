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
        companyInfo.setCompanyName("companyName");
        companyInfo.setCompanyLicense("CompanyLicense");
        companyInfo.setCompanyPhone("CompanyPhone");
        companyInfo.setCompanyRegTime(System.currentTimeMillis());
        companyInfo.setCompanyUpdateTime(System.currentTimeMillis());
        companyInfo.setCompanyStatus(CompanyStatusEnum.NEW.getCode());

        CompanyInfo saveRet = repository.save(companyInfo);
        assertNotNull(saveRet);
    }
}