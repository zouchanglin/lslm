package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.repository.ActivityInfoRepository;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tim
 * @version 1.0
 * @className ActivityServiceImpl
 * @description 活动信息
 * @date 2019-06-20 21:13
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    private final ActivityInfoRepository activityInfoRepository;
    private final CompanyInfoRepository companyInfoRepository;

    @Autowired
    public ActivityServiceImpl(ActivityInfoRepository activityRep,
                               CompanyInfoRepository companyInfoRepository){
        this.activityInfoRepository = activityRep;
        this.companyInfoRepository = companyInfoRepository;
    }

    @Override
    public ActivityInfo findOne(String activityId) {
        return activityInfoRepository.findById(activityId).orElse(null);
    }

    @Override
    public List<ActivityInfo> findAll() {
        return activityInfoRepository.findAll();
    }

    @Override
    public Page<ActivityInfo> findByActivityAudit(Integer activityAudit, Pageable pageable) {
        return activityInfoRepository.findAllByActivityAudit(activityAudit, pageable);
    }

    @Override
    public ActivityInfo save(ActivityInfo activityInfo) {
        return activityInfoRepository.save(activityInfo);
    }

    @Override
    public Boolean delete(String openid, String activityId) {
        Optional<ActivityInfo> findResult = activityInfoRepository.findById(activityId);
        if(findResult.isPresent()){
            ActivityInfo activityInfo = findResult.get();
            if(activityInfo.getActivityOpenid().equals(openid)){
                activityInfoRepository.deleteById(activityId);
                return true;
            }
        }
        return false;
    }

    @Override
    public ActivityInfo create(ActivityInfo activityInfo) {
        String activityOpenid = activityInfo.getActivityOpenid();
        CompanyInfo companyInfo = companyInfoRepository.findOneByOpenid(activityOpenid);
        if(companyInfo == null){
            log.error("[ActivityServiceImpl] 此ActivityInfo无对应企业信息");
            throw new RuntimeException("ActivityInfo无对应企业信息");
        }
//        //逻辑不应该在此处（因为会员同样需要支付）
//        if(!companyInfo.getCompanyMember().equals(MemberLevelEnum.COMMON.getCode())){
//            //推广费用
//            SystemConfig activityGeneralize = systemConfigRepository.findOneByParamsId("activity_generalize");
//            if(activityGeneralize == null) throw new RuntimeException("系统参数丢失");
//            BigDecimal activityGeneralizeMoney = activityGeneralize.getParamsValue().multiply(new BigDecimal(activityInfo.getActivityGeneralize()));
//
//
//            //此活动信息为会员企业创建
//            CompanyOrder companyOrder = new CompanyOrder();
//            companyOrder.setOrderActivity(activityInfo.getActivityId());
//            companyOrder.setOrderMoney(activityGeneralizeMoney);
//            companyOrder.setActivityStatus(ActivityStatusEnum.NEW.getCode());
//            companyOrder.setOrderId(KeyUtil.genUniqueKey());
//            companyOrder.setCreateTime(System.currentTimeMillis());
//            companyOrder.setUpdateTime(System.currentTimeMillis());
//
//            companyOrderRepository.save(companyOrder);
//        }
        return activityInfoRepository.save(activityInfo);
    }


    @Override
    public Page<ActivityInfo> myAllActivity(String openid, PageRequest pageRequest) {
        Page<ActivityInfo> activityInfoPage = activityInfoRepository.findAllByActivityOpenid(openid, pageRequest);
        log.info("[ActivityServiceImpl] activityInfoPage={}", activityInfoPage);
        return activityInfoPage;
    }
}
