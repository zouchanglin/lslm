package edu.xpu.buckmoo.enums;

import lombok.Getter;

@Getter
public enum WeChatPayNotifyEnum implements CodeEnum {

    USER_PAY(1, "用户支付"),
    COMPANY_PAY(2, "企业支付"),
    OTHER_PAY(3, "其他支付款项");

    private Integer code;
    private String message;

    WeChatPayNotifyEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
