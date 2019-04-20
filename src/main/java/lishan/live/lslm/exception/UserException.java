package lishan.live.lslm.exception;

import lishan.live.lslm.enums.ResultEnum;

/**
 * @ClassName UserException
 * @Description
 * @Author tim
 * @Date 2019-04-19 21:10
 * @Version 1.0
 */
public class UserException extends RuntimeException{
    private Integer code;
    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
