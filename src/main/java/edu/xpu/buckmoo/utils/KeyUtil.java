package edu.xpu.buckmoo.utils;

import java.util.Random;

/**
 * @author tim
 * @version 1.0
 * @className KeyUtil
 * @description
 * @date 2019-06-11 14:01
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genVerifyKey() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 10000);
    }
}
