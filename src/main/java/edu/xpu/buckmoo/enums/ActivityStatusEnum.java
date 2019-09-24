package edu.xpu.buckmoo.enums;

import lombok.Getter;


/**
 * 活动通过情况的枚举
 */
@Getter
public enum ActivityStatusEnum implements CodeEnum {
    NEW(0, "未审核"),
    PASS(1, "审核通过"),
    NOT_PASS(2, "审核未通过"),
    FINISH(3, "活动结束")
    ;

    private Integer code;
    private String message;

    ActivityStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
