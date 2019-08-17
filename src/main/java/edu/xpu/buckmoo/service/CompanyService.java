package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CompanyInfo;
import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author tim
 * @version 1.1
 * @className CompanyService
 * @description
 * @date 2019-08-16 22:19
 */
public interface CompanyService {
    /**
     * 根据公司Id寻找公司信息
     * @param companyInfoId 公司Id
     * @return 公司信息，不存在返回null
     */
    CompanyInfo findById(String companyInfoId);

    CompanyInfo findByOpenid(String openid);

    /**
     * 根据状态获取公司列表
     * @param status 状态
     * @param pageable 分页参数
     * @return 公司列表
     */
    Page<CompanyInfo> findByCompanyAudit(Integer status, Pageable pageable);

    /**
     * 删除公司信息
     * @param companyId 公司ID
     */
    void delete(String companyId);

    /**
     * 保存公司信息
     * @param companyInfo 公司信息
     * @return 保存后的公司信息
     */
    CompanyInfo save(CompanyInfo companyInfo);

    /**
     * 公司注册
     * @param companyInfo 公司的注册信息
     * @return 保存的公司注册信息
     */
    CompanyInfo register(CompanyInfo companyInfo);

    /**
     * 生成成为会员的订单
     * @param companyId 公司的id
     * @return 成为会员的订单
     */
    MemberOrder becomeMemberPay(String companyId);
}