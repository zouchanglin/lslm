package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.MemberOrder;
import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.CompanyInfoRepository;
import edu.xpu.buckmoo.repository.MemberOrderRepository;
import edu.xpu.buckmoo.repository.config.SystemConfigRepository;
import edu.xpu.buckmoo.service.CompanyService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.KeyUtil;
import edu.xpu.buckmoo.utils.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tim
 * @version 1.0
 * @className CompanyServiceImpl
 * @description
 * @date 2019-06-20 22:19
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyInfoRepository companyRep;

    @Autowired
    private SystemConfigRepository systemConfigRep;

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private MemberOrderRepository memberOrderRep;

    public CompanyServiceImpl(CompanyInfoRepository companyRep) {
        this.companyRep = companyRep;
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
            companyInfo.setCompanyUpdateTime(System.currentTimeMillis());
            return companyRep.save(companyInfo);
        }else{
            //不存在公司信息就抛出异常
            throw new BuckMooException(ResultEnum.COMPANY_INFO_NOT_EXIT);
        }
    }

    @Override
    public CompanyInfo register(CompanyInfo companyInfo) {
        if(companyInfo.getCompanyId() == null) throw new BuckMooException(ResultEnum.PARAM_ERROR);
        Optional<CompanyInfo> findResult = companyRep.findById(companyInfo.getCompanyId());
        log.info("[公司信息注册] findResult={}", findResult);
        if(findResult.isPresent()){
            log.error("[公司信息注册] companyInfo={}", companyInfo);
            throw new BuckMooException(ResultEnum.ALREADY_EXISTED);
        }else {
            //公司社会统一信用码不符合标准
            if(!VerifyUtil.verifyCompanyId(companyInfo.getCompanyId())){
                log.error("[公司信息注册] CompanyId={}", companyInfo.getCompanyId());
                throw new BuckMooException(ResultEnum.COMPANY_INFO_FORMAT_ERROR);
            }

            //联系方式不符合标准
            if(!VerifyUtil.verifyPhoneNumber(companyInfo.getCompanyPhone())){
                log.error("[公司信息注册] CompanyPhone={}", companyInfo.getCompanyPhone());
                throw new BuckMooException(ResultEnum.COMPANY_INFO_FORMAT_ERROR);
            }
            return companyRep.save(companyInfo);
        }
    }

    @Override
    public MemberOrder becomeMemberPay(String companyId) {
        Optional<CompanyInfo> findResult = companyRep.findById(companyId);
        if(findResult.isPresent()){
            CompanyInfo companyInfo = findResult.get();
            //说明已经是会员
            if(!companyInfo.getCompanyMember().equals(0)){
                throw new BuckMooException(ResultEnum.COMPANY_MEMBER);
            }
            //TODO 设置openid（只能在手机操作，而且方便）
            MemberOrder memberOrder = new MemberOrder();
            memberOrder.setOpenid("TODO.....");
            memberOrder.setOrderId(KeyUtil.genUniqueKey());
            memberOrder.setOrderCompany(companyInfo.getCompanyId());
            memberOrder.setPayStatus(0);
            //年费会员
            Optional<SystemConfig> member_year_money = systemConfigRep.findById("member_year_money");
            if(member_year_money.isPresent()){
                memberOrder.setOrderMoney(member_year_money.get().getParamsValue());
                return memberOrderRep.save(memberOrder);
            }else{
                throw new RuntimeException("数据库配置条目丢失");
            }
        }else{
            log.error("[公司升级为会员-支付] companyId={}", companyId);
            throw new BuckMooException(ResultEnum.COMPANY_INFO_NOT_EXIT);
        }
    }

    @Override
    public PayResponse memberPay(MemberOrder memberOrder) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(memberOrder.getOpenid());
        payRequest.setOrderId(memberOrder.getOrderId());
        payRequest.setOrderAmount(memberOrder.getOrderMoney().doubleValue());
        payRequest.setOrderName(memberOrder.getOrderCompany() + "升级为会员");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("payResponse={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }
}