package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 公司信息JPA接口
 */
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
    Page<CompanyInfo> findAllByCompanyStatus(Integer status, Pageable pageable);

    CompanyInfo findOneByOpenid(String openid);

    @Override
    Page<CompanyInfo> findAll(Pageable pageable);
}
