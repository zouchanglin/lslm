package edu.xpu.buckmoo.utils;

import edu.xpu.buckmoo.enums.CodeEnum;

/**
 * @author tim
 * @version 1.0
 * @className EnumUtil
 * @description
 * @date 2019-06-22 10:04
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
