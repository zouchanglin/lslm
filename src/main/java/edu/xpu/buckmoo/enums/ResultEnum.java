package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * 返回值状态枚举
 */
@Getter
public enum ResultEnum implements CodeEnum{
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    ACTIVITY_ERROR(2, "活动信息不正确"),
    WECHAT_MP_ERROR(3, "微信公众账号方面错误"),
    PART_NOT_EXIT(4, "兼职信息不存在"),
    NULL_FILE(5, "文件为空"),
    WECHAT_PAY_ERROR(6, "微信支付金额校验不通过"),
    ENUM_NOT_EXITS(7, "枚举不存在")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
