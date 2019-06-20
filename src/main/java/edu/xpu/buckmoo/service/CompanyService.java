package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CompanyInfo;

/**
 * @author tim
 * @version 1.0
 * @className CompanyService
 * @description
 * @date 2019-06-20 22:19
 */
public interface CompanyService {
    CompanyInfo findOne(String companyInfoId);
}
