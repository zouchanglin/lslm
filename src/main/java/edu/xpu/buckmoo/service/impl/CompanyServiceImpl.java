package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className CompanyServiceImpl
 * @description
 * @date 2019-06-20 22:19
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyInfoRepository companyRep;

    @Override
    public CompanyInfo findOne(String companyInfoId) {
        return companyRep.findById(companyInfoId).orElse(null);
    }

    @Override
    public CompanyInfo findCompanyInfoByOpenid(String openId) {
        return companyRep.findByOpenId(openId);
    }

    @Override
    public Page<CompanyInfo> findByCompanyAudit(Integer status, Pageable pageable) {
        return companyRep.findAllByCompanyStatus(status, pageable);
    }

    @Override
    public void delete(String companyId) {
        companyRep.deleteById(companyId);
    }

    @Override
    public CompanyInfo save(CompanyInfo companyInfo) {
        return companyRep.save(companyInfo);
    }
}
