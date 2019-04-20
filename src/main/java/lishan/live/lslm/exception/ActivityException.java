package lishan.live.lslm.exception;

import lishan.live.lslm.enums.ResultEnum;

/**
 * @ClassName ActivityException
 * @Description
 * @Author tim
 * @Date 2019-04-19 21:09
 * @Version 1.0
 */
public class ActivityException  extends RuntimeException{
    private Integer code;

    public ActivityException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ActivityException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
