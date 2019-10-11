package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.UserInfo;

/**
 * @author tim
 * @version 1.0
 * @className UserInfoService
 * @description 用户信息相关服务
 * @date 2019-06-21 21:50
 */
public interface UserInfoService {

    /**
     * 向数据库存储一个用户信息
     * @param userInfo 要存储的用户信息
     * @return 返回存储结果
     */
    UserInfo saveUser(UserInfo userInfo);

    /**
     * 根据用户唯一标识查找用户
     * @param openId 用户唯一标识openid
     * @return 用户信息数据
     */
    UserInfo findById(String openId);

    /**
     * 用户总数统计
     * @return 用户总数
     */
    Integer userCount();

    /**
     * 把用户升级为某个会员
     * @param memberLevel 会员等级
     * @param openid 用户的唯一标识openid
     * @return 更新后的用户信息
     */
    UserInfo updateUserToMember(Integer memberLevel, String openid);
}
