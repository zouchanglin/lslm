package edu.xpu.buckmoo.exception;

import edu.xpu.buckmoo.enums.ResultEnum;

/**
 * @author tim
 * @version 1.1
 * @className BuckMooException
 * @description BuckMooException定义了项目中出现的异常
 * @date 2019-06-19 19:46
 */
public class BuckMooException extends RuntimeException{

    private Integer code;

    public BuckMooException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BuckMooException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}