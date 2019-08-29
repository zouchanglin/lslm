package edu.xpu.buckmoo.convert;

import edu.xpu.buckmoo.VO.ActivityInfoVO;
import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.enums.ActivityModeEnum;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.utils.EnumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ActivityInfo2VO {
    private final CompanyInfoRepository companyInfoRepository;

    public ActivityInfo2VO(CompanyInfoRepository companyInfoRepository) {
        this.companyInfoRepository = companyInfoRepository;
    }

    public ActivityInfoVO activityToVO(ActivityInfo activityInfo){
        ActivityInfoVO activityInfoVO = new ActivityInfoVO();
        BeanUtils.copyProperties(activityInfo, activityInfoVO);

        ActivityStatusEnum activityStatusEnum = EnumUtil.getByCode(activityInfo.getActivityAudit(), ActivityStatusEnum.class);
        assert activityStatusEnum != null;
        activityInfoVO.setActivityAuditStr(activityStatusEnum.getMessage());

        ActivityModeEnum activityModeEnum = EnumUtil.getByCode(activityInfo.getActivityMode(), ActivityModeEnum.class);
        assert activityModeEnum != null;
        activityInfoVO.setActivityModeStr(activityModeEnum.getMessage());

        String activityOpenid = activityInfo.getActivityOpenid();

        try {
            CompanyInfo companyInfo = companyInfoRepository.findOneByOpenid(activityOpenid);
            activityInfoVO.setActivityOpenidStr(companyInfo.getCompanyName());
        }catch (Exception e){

        }
        return activityInfoVO;
    }
}
