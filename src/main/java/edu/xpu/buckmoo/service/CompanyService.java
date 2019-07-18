package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className CompanyService
 * @description
 * @date 2019-06-20 22:19
 */
public interface CompanyService {
    CompanyInfo findOne(String companyInfoId);

    CompanyInfo findCompanyInfoByOpenid(String openId);

    Page<CompanyInfo> findByCompanyAudit(Integer status, Pageable pageable);

    void delete(String companyId);

    CompanyInfo save(CompanyInfo companyInfo);
}
