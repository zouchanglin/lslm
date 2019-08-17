package edu.xpu.buckmoo.enums;

import lombok.Getter;

/**
 * @author tim
 * @version 1.0
 * @className PartTimeStatusEnum
 * @description 兼职订单状态枚举
 * @date 2019-06-11 21:23
 */
@Getter
public enum  PartTimeStatusEnum implements CodeEnum{
    NO_PAY(0, "初始化"),
    NEW_PART(1, "未审核"),
    NOT_PASS(2, "审核失败(已退款)"),
    PASS_PAY(3, "已发布"),
    TAKE_ORDER(4, "已接单"),
    FINISH_ORDER(5, "任务完成"),
    FINISH_CREATE(6, "发布方已确认"),
    CANCEL_CREATE(7, "取消订单"),
    OTHER(8, "其他情况")
    ;

    private Integer code;

    private String message;

    PartTimeStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PartTimeStatusEnum getOneByStatus(Integer status){
        for(PartTimeStatusEnum partTimeStatusEnum: values()){
            if(partTimeStatusEnum.getCode().equals(status))
                return partTimeStatusEnum;
        }
        return null;
    }
}
