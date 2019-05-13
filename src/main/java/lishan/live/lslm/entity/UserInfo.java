package lishan.live.lslm.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName UserInfo
 * @Description
 * @Author tim
 * @Date 2019-04-14 21:17
 * @Version 1.0
 */
@Entity
@Data
@DynamicUpdate
public class UserInfo {

    /**
     * 用户OpenId
     */
    @Id
    private String userOpenid;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户学校
     */
    private String userSchool;

    /**
     * 手机号码
     */
    private String userPhone;

    /**
     * 用户类型（0是未毕业，1是已毕业）
     */
    private Integer userType;


    /**
     * 用户注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userRegdate;

    /**
     * 用户积分
     */
    private Integer userIntegral;

    /**
     * 备注信息
     */
    private String userOther;
}
