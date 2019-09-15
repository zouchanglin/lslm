package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * 返回值状态枚举
 */
@Getter
public enum ErrorResultEnum implements CodeEnum{
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    ACTIVITY_ERROR(2, "活动信息不正确"),
    WECHAT_MP_ERROR(3, "微信公众账号方面错误"),
    PART_NOT_EXIT(4, "兼职信息不存在"),
    NULL_FILE(5, "文件为空"),
    WECHAT_PAY_ERROR(6, "微信支付金额校验不通过"),
    ENUM_NOT_EXITS(7, "枚举不存在"),
    PART_STATUS_ERROR(8, "兼职状态不正确"),
    ALREADY_EXISTED(9, "此信息已经存在"),
    COMPANY_INFO_FORMAT_ERROR(10, "社会统一信用码格式错误"),
    COMPANY_INFO_NOT_EXIT(11, "公司信息不存在"),
    COMPANY_MEMBER(12, "公司已经是会员"),
    THIS_ORDER_NOT_EXITS(13, "此统一订单不存在"),
    COMPANY_STATUS_ERROR(14, "公司审核状态不正确")
    ;

    private Integer code;

    private String message;

    ErrorResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
