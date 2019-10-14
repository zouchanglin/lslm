package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CollectionOrder;


public interface MemberService {
    /**
     * 普通用户升级为会员
     * @param memberLevel 要升级的会员等级
     * @param openid 用户的唯一标识符
     * @return 统一订单信息
     */
    CollectionOrder addNewUserMember(int memberLevel, String openid);

    /**
     * 已经是会员的用户升级会员
     * @param memberLevel 要升级的会员等级
     * @param openid 用户唯一标识符
     * @return 统一订单信息
     */
    CollectionOrder changeUserMember(int memberLevel, String openid);
}
