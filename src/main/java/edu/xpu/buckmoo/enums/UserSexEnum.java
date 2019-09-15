package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * 用户性别枚举
 */
@Getter
public enum UserSexEnum implements CodeEnum{
    MAN(1, "男"),
    WOMAN(2, "女"),
    OTHER(3, "男女不限");

    private Integer code;
    private String message;

    UserSexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
