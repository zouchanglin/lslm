package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * 活动类型的枚举
 */
@Getter
public enum ActivityModeEnum implements CodeEnum {
    COMPANY(0, "企业组织活动"),
    STUDENT(1, "学生社团活动"),
    OTHER(2, "其他类型")
    ;

    private Integer code;

    private String message;

    ActivityModeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
