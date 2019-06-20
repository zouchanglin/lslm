package edu.xpu.buckmoo.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author tim
 * @version 1.0
 * @className JsonUtil
 * @description
 * @date 2019-06-20 22:54
 */
public class JsonUtil {
    public static String toJson(Object object) {
        return JSONObject.toJSONString(object, true);
    }
}
