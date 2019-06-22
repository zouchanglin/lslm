package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 公司信息JPA接口
 */
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
    CompanyInfo findByOpenId(String openId);
}
