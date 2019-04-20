package lishan.live.lslm.convert;

import lishan.live.lslm.dto.ActivityInfoDTO;
import lishan.live.lslm.entity.ActivityInfo;
import lishan.live.lslm.entity.CompanyInfo;
import lishan.live.lslm.repository.CompanyInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName Activity2ActivityDTO
 * @Description
 * @Author tim
 * @Date 2019-04-20 12:06
 * @Version 1.0
 */
@Component
public class Activity2ActivityDTO {
    private static CompanyInfoRepository companyInfoRepository;

    @Autowired
    public Activity2ActivityDTO(CompanyInfoRepository companyInfoRepository) {
        Activity2ActivityDTO.companyInfoRepository = companyInfoRepository;
    }

    public static ActivityInfoDTO convert(ActivityInfo activityInfo){
        ActivityInfoDTO activityInfoDTO = new ActivityInfoDTO();

        BeanUtils.copyProperties(activityInfo, activityInfoDTO);

        String[] companyId_list = activityInfo.getActivitySponsor().split("#");
        List<String> companyNameMain = new ArrayList<>();
        List<String> companyNameOther = new ArrayList<>();
        analysis(companyId_list, companyNameMain);


        companyId_list = activityInfo.getActivityUndertake().split("#");
        analysis(companyId_list, companyNameOther);

        activityInfoDTO.setActivitySponsorName(companyNameMain);
        activityInfoDTO.setActivityUnderName(companyNameOther);

        return activityInfoDTO;
    }

    private static void analysis(String[] companyId_list, List<String> companyNameOther) {
        for(String companyId:companyId_list){
            Optional<CompanyInfo> byId = companyInfoRepository.findById(companyId);
            assert byId.orElse(null) != null;
            companyNameOther.add(byId.orElse(null).getCompanyName());
        }
    }


    public static List<ActivityInfoDTO> convert(List<ActivityInfo> activityInfoList){
        List<ActivityInfoDTO> retList = new ArrayList<>();
        for(ActivityInfo activityInfo: activityInfoList){
            retList.add(convert(activityInfo));
        }
        return retList;
    }
}
