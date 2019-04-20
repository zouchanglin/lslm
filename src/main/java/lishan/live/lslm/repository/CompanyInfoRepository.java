package lishan.live.lslm.repository;

import lishan.live.lslm.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName CompanyRepository
 * @Description 操作公司信息CompanyInfo表的类
 * @Author tim
 * @Date 2019-04-14 16:40
 * @Version 1.0
 */
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
    /**
     * 根据公司状态查询符合条件的公司
     * @param productStatus 公司状态
     * @return 符合条件的公司的List
     */
    List<CompanyInfo> findByCompanyStatus(Integer productStatus);

    List<CompanyInfo> findByCompanyNameLike(String companyName);

}
