package lishan.live.lslm.service;

import lishan.live.lslm.entity.CompanyInfo;

import java.util.List;

/**
 * @ClassName CompanyService
 * @Description
 * @Author tim
 * @Date 2019-04-18 17:53
 * @Version 1.0
 */

public interface CompanyService {
    CompanyInfo findCompanyInfoById(String companyId);

    CompanyInfo saveCompanyInfo(CompanyInfo companyInfo);

    List<CompanyInfo> findAllCompanyInfo();

    //公司名称相似匹配查询
    List<CompanyInfo> findAllCompanyInfoNameLike(String companyName);

    void deleteCompanyInfo(String companyId);
}
