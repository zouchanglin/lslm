package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.MemberLevelEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author tim
 * @version 1.2
 * @className UserInfo
 * @description 用户信息实体类
 * @date 2019-06-10 23:23
 */
@Data
@Entity
@DynamicUpdate
public class UserInfo {
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
    private Integer userMember = MemberLevelEnum.COMMON.getCode();


    /**
     * 企业Id，为空或者为""都是普通用户
     */
    private String companyId;
}