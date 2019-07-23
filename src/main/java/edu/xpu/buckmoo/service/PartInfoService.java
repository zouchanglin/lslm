package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.PartInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import retrofit2.http.Part;

/**
 * 此接口是兼职信息的操作接口
 */
public interface PartInfoService {

    /**
     * 查找一个兼职信息
     * @param partId 兼职信息主键
     * @return 兼职信息实体
     */
    PartInfo findOneById(String partId);

    /**
     * 根据兼职分类进行分页查询
     * @param category 兼职分类
     * @param pageable 分页参数
     * @return 分页查询结果
     */
    Page<PartInfo> listByCategory(Integer category, Pageable pageable);

    /**
     * 根据兼职分类和状态进行分页查询
     * @param category 兼职分类
     * @param status 兼职状态
     * @param pageable 分页参数
     * @return 分页查询结果
     */
    Page<PartInfo> listByCategoryAndStatus(Integer category, Integer status, Pageable pageable);

    /**
     * 新增/保存一条兼职信息
     * @param partInfo 兼职信息实体
     * @return 保存后的兼职信息
     */
    PartInfo addOnePartTime(PartInfo partInfo);

    /**
     * 根据用户发布的兼职状态分页查询
     * @param openid 用户的openId
     * @param pageRequest 分页参数
     * @param status 兼职信息的状态
     * @return 分页查询结果
     */
    Page<PartInfo> listByUserCreate(String openid, PageRequest pageRequest, Integer status);

    /**
     * 根据用户接受的兼职状态分页查询
     * @param openid 用户的openId
     * @param pageRequest 分页参数
     * @param status 兼职信息的状态
     * @return 分页查询结果
     */
    Page<PartInfo> listByUserAccept(String openid, PageRequest pageRequest, Integer status);

    /**
     * 修改兼职信息状态
     * @param orderId 兼职信息Id
     * @param code 要修改的状态
     * @return 修改并保存后的兼职信息
     */
    PartInfo modifyPartStatus(String orderId, Integer code);

    /**
     * 根据用户发布的兼职分页查询(所有)
     * @param openid 用户的openId
     * @param pageRequest 分页参数
     * @return 分页查询结果
     */
    Page<PartInfo> userAllCreate(String openid, PageRequest pageRequest);

    /**
     * 根据用户接受的兼职分页查询(所有)
     * @param openid 用户的openId
     * @param pageRequest 分页参数
     * @return 分页查询结果
     */
    Page<PartInfo> userAllAccept(String openid, PageRequest pageRequest);


    PartInfo acceptOnePart(String openid, String partId);

    PartInfo finishOnePart(String openid, String partId);

    PartInfo affirmFinishPart(String openid, String partId);
}