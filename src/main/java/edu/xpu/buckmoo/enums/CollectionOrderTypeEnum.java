package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * 支付类型的枚举，方便统一回调
 */
@Getter
public enum CollectionOrderTypeEnum implements CodeEnum {
    USER_PART_PAY(0, "用户兼职支付"),
    COMPANY_ACTIVITY_PAY(1, "企业活动支付"),
    COMPANY_MEMBER_PAY(2, "企业会员支付"),
    USER_MEMBER_PAY(3, "用户会员支付"),
    OTHER_PAY(4, "其他支付")
    ;

    private Integer code;
    private String message;

    CollectionOrderTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
