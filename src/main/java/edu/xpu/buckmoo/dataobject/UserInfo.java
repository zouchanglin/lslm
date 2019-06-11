package edu.xpu.buckmoo.dataobject;

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

    @Id
    private String userId;

    /**
     * 微信的openId
     */
    private String openId;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户积分
     */
    private Integer userGrade;

    /**
     * 信息注册时间
     */
    private Date createTime;

    /**
     * 信息更新时间
     */
    private Date updateTime;
}
