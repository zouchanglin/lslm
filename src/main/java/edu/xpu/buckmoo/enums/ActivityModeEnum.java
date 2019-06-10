package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * 活动类型的枚举
 */
@Getter
public enum ActivityModeEnum implements CodeEnum {
    STUDENT(0, "学生社团活动"),
    COMPANY(1, "企业组织活动"),
    OTHER(2, "其他类型")
    ;

    private Integer code;

    private String message;

    ActivityModeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
