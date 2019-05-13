package lishan.live.lslm.enums;

import lombok.Getter;

/**
 * @ClassName ResultEnum
 * @Description
 * @Author tim
 * @Date 2019-04-19 21:11
 * @Version 1.0
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    ENTITY_NOT_EXIST(2,"实体不存在"),
    WECHAT_MP_ERROR(3, "微信网页授权异常")
    ;


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
