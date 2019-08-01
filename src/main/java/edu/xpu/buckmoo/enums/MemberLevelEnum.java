package edu.xpu.buckmoo.enums;

import lombok.Getter;

@Getter
public enum MemberLevelEnum implements CodeEnum{
    COMMON(0, "非会员"),
    ONE_LEVEL(1, "白银会员"),
    TWO_LEVEL(2, "黄金会员"),
    THREE_LEVEL(3, "钻石会员")
    ;

    private Integer code;

    private String message;

    MemberLevelEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
