package edu.xpu.buckmoo.VO;

import lombok.Data;

/**
 * @author tim
 * @version 1.0
 * @className ResultVO
 * @description
 * @date 2019-06-19 22:16
 */
@Data
public class ResultVO<T> {
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
