package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
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
public class CompanyInfoRepositoryTest {
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Test
    public void save(){
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setOpenid(KeyUtil.genUniqueKey());
        companyInfo.setCompanyId(KeyUtil.genUniqueKey());
        companyInfo.setCompanyStatus(CompanyStatusEnum.NEW.getCode());
        companyInfo.setCompanyName("公司名称");
        companyInfo.setCompanyMember(MemberLevelEnum.COMMON.getCode());
        companyInfo.setCompanyPhone("电话");
        companyInfo.setMemberOverdue(System.currentTimeMillis());
        companyInfo.setCompanyLicense("http://sswsw.png");

        CompanyInfo save = companyInfoRepository.save(companyInfo);
        assertNotNull(save);
        log.info("[CompanyInfoRepositoryTest] save={}", save);
    }


    @Test
    public void findOne(){
        CompanyInfo oneByOpenid = companyInfoRepository.findOneByOpenid("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
        System.out.println(oneByOpenid);
    }
}