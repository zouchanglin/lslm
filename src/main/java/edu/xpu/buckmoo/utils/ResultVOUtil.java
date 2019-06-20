package edu.xpu.buckmoo.utils;

import edu.xpu.buckmoo.VO.ResultVO;

/**
 * @author tim
 * @version 1.0
 * @className ResultVOUtil
 * @description
 * @date 2019-06-20 21:41
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
