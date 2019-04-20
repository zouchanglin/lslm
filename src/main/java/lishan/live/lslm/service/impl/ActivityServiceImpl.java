package lishan.live.lslm.service.impl;

import lishan.live.lslm.convert.Activity2ActivityDTO;
import lishan.live.lslm.dto.ActivityInfoDTO;
import lishan.live.lslm.entity.ActivityInfo;
import lishan.live.lslm.repository.ActivityInfoRepository;
import lishan.live.lslm.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ActivityServiceImpl
 * @Description
 * @Author tim
 * @Date 2019-04-19 22:06
 * @Version 1.0
 */

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    private final ActivityInfoRepository activityInfoRepository;

    @Autowired
    public ActivityServiceImpl(ActivityInfoRepository activityInfoRepository) {
        this.activityInfoRepository = activityInfoRepository;
    }


    @Override
    public ActivityInfo findActivityInfoById(Integer activityId) {
        Optional<ActivityInfo> byId = activityInfoRepository.findById(activityId);
        ActivityInfo myNullActivity = new ActivityInfo();
        return byId.orElse(myNullActivity);
    }

    @Override
    public ActivityInfo saveActivityInfo(ActivityInfo activityInfo) {
        ActivityInfo saveResult = activityInfoRepository.save(activityInfo);
        log.info("【ActivityServiceImpl:saveActivityInfo】saveResult={}", saveResult);
        return saveResult;
    }

    @Override
    public List<ActivityInfo> findAllActivityInfo() {
        List<ActivityInfo> activityInfoList = activityInfoRepository.findAll();
        log.info("【ActivityServiceImpl:findAllActivityInfo】activityInfoList={}", activityInfoList);
        return activityInfoList;
    }

    @Override
    public List<ActivityInfo> findAllActivityInfoNameLike(String activityName) {
        List<ActivityInfo> nameLike = activityInfoRepository.findByActivityNameLike("%" + activityName + "%");
        log.info("【ActivityServiceImpl:findAllActivityInfoNameLike】nameLike={}", nameLike);
        return nameLike;
    }

    @Override
    public void deleteActivityInfo(Integer activityId) {
        activityInfoRepository.deleteById(activityId);
    }

    @Override
    public List<ActivityInfoDTO> findAllActivityInfoDTO() {
        return Activity2ActivityDTO.convert(activityInfoRepository.findAll());
    }

    @Override
    public List<ActivityInfoDTO> findAllActivityInfoDTONameLike(String activityName) {
        return Activity2ActivityDTO.convert(activityInfoRepository.findByActivityNameLike("%" + activityName + "%"));
    }
}
