package edu.xpu.buckmoo.dataobject;

import edu.xpu.buckmoo.enums.MemberLEVELEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author tim
 * @version 1.0
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
    private String userAddress;

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
    private Date createTime;

    /**
     * 信息更新时间
     */
    private Date updateTime;

    /**
     * 会员等级
     */
    private Integer userMember = MemberLEVELEnum.COMMON.getCode();
}
