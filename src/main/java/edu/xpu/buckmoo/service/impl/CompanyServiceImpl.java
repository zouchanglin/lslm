package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.enums.CompanyStatusEnum;
import edu.xpu.buckmoo.enums.ErrorResultEnum;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.repository.order.MemberOrderRepository;
import edu.xpu.buckmoo.repository.config.SystemConfigRepository;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.EnumUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tim
 * @version 1.2
 * @className CompanyServiceImpl
 * @description 企业信息服务实现
 * @date 2019-06-20 22:19
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyInfoRepository companyRep;
    private final SystemConfigRepository systemConfigRep;
    private final UserInfoService userInfoService;

    private final MemberOrderRepository memberOrderRep;

    public CompanyServiceImpl(CompanyInfoRepository companyRep, SystemConfigRepository systemConfigRep, UserInfoService userInfoService, MemberOrderRepository memberOrderRep) {
        this.companyRep = companyRep;
        this.systemConfigRep = systemConfigRep;
        this.userInfoService = userInfoService;
        this.memberOrderRep = memberOrderRep;
    }

    @Override
    public CompanyInfo findById(String companyInfoId) {
        return companyRep.findById(companyInfoId).orElse(null);
    }
    @Override
    public Page<CompanyInfo> findByCompanyAudit(Integer status, Pageable pageable) {
        return companyRep.findAllByCompanyStatus(status, pageable);
    }
    @Override
    public void delete(String companyId) {
        companyRep.deleteById(companyId);
    }

    @Override
    public CompanyInfo save(CompanyInfo companyInfo) {
        Optional<CompanyInfo> findResult = companyRep.findById(companyInfo.getCompanyId());
        if(findResult.isPresent()){
            log.info("[公司信息更新] findResult={}", findResult);
            //更新时间的设置
            return companyRep.save(companyInfo);
        }else{
            //不存在公司信息就抛出异常
            throw new BuckMooException(ErrorResultEnum.COMPANY_INFO_NOT_EXIT);
        }
    }

    @Override
    public CompanyInfo register(CompanyInfo companyInfo) {
        if(companyInfo.getCompanyId() == null) throw new BuckMooException(ErrorResultEnum.PARAM_ERROR);
        Optional<CompanyInfo> findResult = companyRep.findById(companyInfo.getCompanyId());
        log.info("[公司信息注册] findResult={}", findResult);
        if(findResult.isPresent()){
            log.error("[公司信息注册] companyInfo={}", companyInfo);
            throw new BuckMooException(ErrorResultEnum.ALREADY_EXISTED);
        }else {
            companyInfo.setMemberOverdue(System.currentTimeMillis());
            //
            UserInfo userInfo = userInfoService.findById(companyInfo.getOpenid());
            userInfo.setCompanyId(companyInfo.getCompanyId());
            userInfoService.saveUser(userInfo);
            return companyRep.save(companyInfo);
        }
    }

    @Override
    public MemberOrder becomeMemberPay(String companyId, Integer memberLevel) {
        Optional<CompanyInfo> findResult = companyRep.findById(companyId);
        if(findResult.isPresent()){
            CompanyInfo companyInfo = findResult.get();
            //说明已经是会员
            if(!companyInfo.getCompanyMember().equals(0)){
                throw new BuckMooException(ErrorResultEnum.COMPANY_MEMBER);
            }
            MemberOrder memberOrder = new MemberOrder();
            memberOrder.setOrderId(KeyUtil.genUniqueKey());
            memberOrder.setOrderCompany(companyInfo.getCompanyId());
            memberOrder.setPayStatus(0);

            Optional<SystemConfig> member_money = Optional.empty();
            if(memberLevel.equals(MemberLevelEnum.ONE_LEVEL.getCode())){
                //月费会员
                member_money = systemConfigRep.findById("member_month_money");
                memberOrder.setMemberLevel(MemberLevelEnum.ONE_LEVEL.getCode());
            }else if(memberLevel.equals(MemberLevelEnum.TWO_LEVEL.getCode())){
                //年费会员
                member_money = systemConfigRep.findById("member_year_money");
                memberOrder.setMemberLevel(MemberLevelEnum.TWO_LEVEL.getCode());
            }else if(memberLevel.equals(MemberLevelEnum.THREE_LEVEL.getCode())){
                //永久会员
                member_money = systemConfigRep.findById("member_forever_money");
                memberOrder.setMemberLevel(MemberLevelEnum.THREE_LEVEL.getCode());
            }


            if(member_money.isPresent()){
                memberOrder.setOrderMoney(member_money.get().getParamsValue());
                return memberOrderRep.save(memberOrder);
            }else{
                throw new RuntimeException("数据库配置条目丢失");
            }
        }else{
            log.error("[公司升级为会员-支付] companyId={}", companyId);
            throw new BuckMooException(ErrorResultEnum.COMPANY_INFO_NOT_EXIT);
        }
    }

    @Override
    public CompanyInfo findByOpenid(String openid) {
        return companyRep.findOneByOpenid(openid);
    }

    @Override
    public Page<CompanyInfo> findAll(PageRequest of) {
        return companyRep.findAll(of);
    }

    @Override
    public Integer companyCount() {
        return companyRep.findAll().size();
    }

    @Override
    public CompanyInfo audit(String companyId, Integer companyStatus) {
        Optional<CompanyInfo> byId = companyRep.findById(companyId);
        if(byId.isPresent()){
            CompanyInfo companyInfo = byId.get();
            CompanyStatusEnum statusEnum = EnumUtil.getByCode(companyStatus, CompanyStatusEnum.class);
            if(statusEnum == null){
                throw new RuntimeException("参数状态不在企业中");
            }
            companyInfo.setCompanyStatus(companyStatus);
            return companyRep.save(companyInfo);
        }else{
            throw new RuntimeException("企业信息不存在");
        }
    }
}