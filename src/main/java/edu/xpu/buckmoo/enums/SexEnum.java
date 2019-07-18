package edu.xpu.buckmoo.enums;

import lombok.Getter;

@Getter
public enum SexEnum implements CodeEnum{
    MAN(1, "男"),
    WOMAN(2, "女"),
    OTHER(3, "男女不限");

    private Integer code;
    private String message;

    SexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
