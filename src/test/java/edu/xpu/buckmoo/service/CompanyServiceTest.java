package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Test
    public void findB(){
        Page<CompanyInfo> byCompanyAudit = companyService.findByCompanyAudit(1, PageRequest.of(0, 10));
        for(CompanyInfo companyInfo: byCompanyAudit.getContent()){
            System.out.println(companyInfo);
        }
    }
}