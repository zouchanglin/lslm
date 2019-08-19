package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.ActivityInfo;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.order.CompanyOrder;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.repository.ActivityInfoRepository;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.repository.order.CompanyOrderRepository;
import edu.xpu.buckmoo.service.ActivityService;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
    private final CompanyOrderRepository companyOrderRepository;

    @Autowired
    public ActivityServiceImpl(ActivityInfoRepository activityRep, CompanyInfoRepository companyInfoRepository, CompanyOrderRepository companyOrderRepository) {
        this.activityInfoRepository = activityRep;
        this.companyInfoRepository = companyInfoRepository;
        this.companyOrderRepository = companyOrderRepository;
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
    public void delete(String activityId) {
        activityInfoRepository.deleteById(activityId);
    }

    @Override
    public ActivityInfo create(ActivityInfo activityInfo) {
        String activityOpenid = activityInfo.getActivityOpenid();
        CompanyInfo companyInfo = companyInfoRepository.findOneByOpenid(activityOpenid);
        if(companyInfo == null){
            log.error("[ActivityServiceImpl] 此ActivityInfo无对应企业信息");
            throw new RuntimeException("ActivityInfo无对应企业信息");
        }
        if(!companyInfo.getCompanyMember().equals(MemberLevelEnum.COMMON.getCode())){
            //此活动信息为会员企业创建
            CompanyOrder companyOrder = new CompanyOrder();
            companyOrder.setOrderActivity(activityInfo.getActivityId());
            companyOrder.setOrderMoney(new BigDecimal(0));
            companyOrder.setActivityStatus(ActivityStatusEnum.NEW.getCode());
            companyOrder.setOrderId(KeyUtil.genUniqueKey());
            companyOrder.setCreateTime(System.currentTimeMillis());
            companyOrder.setUpdateTime(System.currentTimeMillis());

            companyOrderRepository.save(companyOrder);
        }
        return activityInfoRepository.save(activityInfo);
    }
}
