package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //TODO 完成扫码登录
    }
}
