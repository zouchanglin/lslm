package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * 支付状态的枚举
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    NOT_PAY(0, "未支付"),
    PAY_FINISH(1, "已经支付"),
    ORDER_FINISH(2, "已完结"),
    OTHER(3, "其他")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
