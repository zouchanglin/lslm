package edu.xpu.buckmoo.VO;


import lombok.Data;

import javax.persistence.Id;
@Data
public class UserInfoVO {
    /**
     * 微信的openId
     */
    @Id
    private String openId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 用户性别
     */
    private String userSexStr;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 用户地址
     */
    private String userCity;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户积分
     */
    private Integer userGrade = 0;

    /**
     * 信息注册时间
     */
    private Long createTime;

    /**
     * 信息更新时间
     */
    private Long updateTime;

    /**
     * 会员等级
     */
    private String userMemberStr;

    private Integer userMember;

    /**
     * 如果是管理员：企业Id
     */
    private String companyId;
}
