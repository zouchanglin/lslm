package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 公司信息JPA接口
 */
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
    CompanyInfo findByLoginOpenid(String openId);

    Page<CompanyInfo> findAllByCompanyStatus(Integer status, Pageable pageable);
}
