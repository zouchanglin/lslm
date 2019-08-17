package edu.xpu.buckmoo.VO;

import edu.xpu.buckmoo.dataobject.order.PartInfo;
import lombok.Data;


/**
 * 向前台返回的关于兼职的数据
 */
@Data
public class PartInfoOther extends PartInfo {

    /**
     * 性别
     */
    private String employSexStr;

    /**
     * 分类
     */
    private String partCategoryStr;

    /**
     * 微信用户名(发布者)
     */
    private String partCreatorStr;

    /**
     * 微信用户名（接受者）
     */
    private String partEmployStr;


    /**
     * 接受者手机号码
     */
    private String partEmployPhone;

    /**
     * 状态
     */
    private String partStatusStr;
}
