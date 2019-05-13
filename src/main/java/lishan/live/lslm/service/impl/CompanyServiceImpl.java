package lishan.live.lslm.service.impl;

import lishan.live.lslm.entity.CompanyInfo;
import lishan.live.lslm.repository.CompanyInfoRepository;
import lishan.live.lslm.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName CompanyServiceImpl
 * @Description
 * @Author tim
 * @Date 2019-04-18 17:55
 * @Version 1.0
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyInfoRepository companyInfoRepository;

    @Autowired
    public CompanyServiceImpl(CompanyInfoRepository companyInfoRepository) {
        this.companyInfoRepository = companyInfoRepository;
    }

    @Override
    public CompanyInfo findCompanyInfoById(String companyId) {
        Optional<CompanyInfo> byId = companyInfoRepository.findById(companyId);
        CompanyInfo info = new CompanyInfo();
        return byId.orElse(info);
    }

    @Override
    public CompanyInfo saveCompanyInfo(CompanyInfo companyInfo) {
        log.info("【CompanyServiceImpl:saveCompanyInfo】companyInfo = {}", companyInfo);
        CompanyInfo save = companyInfoRepository.save(companyInfo);
        log.info("【CompanyServiceImpl:saveCompanyInfo】saveRet = {}", save);
        return save;
    }

    @Override
    public List<CompanyInfo> findAllCompanyInfo() {
        List<CompanyInfo> companyInfoList = companyInfoRepository.findAll();
        log.info("【CompanyServiceImpl:saveCompanyInfo】findAllRet = {}", companyInfoList);
        return companyInfoList;
    }

    @Override
    public List<CompanyInfo> findAllCompanyInfoNameLike(String companyName) {
        List<CompanyInfo> companyInfoList = companyInfoRepository.findByCompanyNameLike("%"+companyName+"%");
        log.info("【CompanyServiceImpl:findAllCompanyInfoNameLike】companyInfoList = {}", companyInfoList);
        return companyInfoList;
    }

    @Override
    public void deleteCompanyInfo(String companyId) {
        companyInfoRepository.deleteById(companyId);
    }
}
