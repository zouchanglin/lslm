package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * @author tim
 * @version 1.0
 * @className CompanyStatusEnum
 * @description 公司审核状态枚举
 * @date 2019-06-11 14:31
 */
@Getter
public enum CompanyStatusEnum implements CodeEnum {
    NEW(0, "未审核"),
    PASS(1, "审核通过"),
    NOT_PASS(2, "审核未通过"),
    ;

    private Integer code;

    private String message;

    CompanyStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
