package edu.xpu.buckmoo.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    ACTIVITY_ERROR(2, "活动信息不正确"),
    WECHAT_MP_ERROR(3, "微信公众账号方面错误"),
    PART_NOT_EXIT(4, "兼职信息不存在"),
    NULL_FILE(5, "文件为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
