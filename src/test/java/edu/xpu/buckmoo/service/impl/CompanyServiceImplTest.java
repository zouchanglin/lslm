package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.service.CompanyService;
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
public class CompanyServiceImplTest {
    @Autowired
    private CompanyService service;
    @Test
    public void findOne() {
        CompanyInfo one = service.findOne("1560234861488151072");
        log.info("CompanyInfo={}", one);
        assertNotNull(one);
    }

    @Test
    public void findCompanyInfoByOpenid() {
        CompanyInfo openid = service.findCompanyInfoByOpenid("123");
        assertNotNull(openid);
    }
}