package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * @author tim
 * @version 1.0
 * @className PartTimeStatusEnum
 * @description 兼职订单枚举
 * @date 2019-06-11 21:23
 */
@Getter
public enum  PartTimeStatusEnum implements CodeEnum{
    NEW_PART(0, "未审核"),
    PASS_NO_PAY(1, "审核通过 未付款"),
    PASS_PAY(2, "审核 已经付款"),
    TAKE_ORDER(3, "已经接单"),
    FINISH_ORDER(4, "已经完成"),
    FINISH_CREATE(5, "发布方确认已完成"),
    CANCEL_CREATE(6, "取消订单 未付款"),
    CANCEL_PAY(7, "取消订单 已付款/接单"),
    OTHER(8, "其他情况")
    ;

    private Integer code;

    private String message;

    PartTimeStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
