package edu.xpu.buckmoo.utils;

/**
 * 格式验证工具类
 */
public class VerifyUtil {
    //15位和18位社会统一信用码
    private static final String COMPANY_ID_VERIFY = "/(^(?:(?![IOZSV])[\\dA-Z]){2}\\d{6}(?:(?![IOZSV])[\\dA-Z]){10}$)|(^\\d{15}$)/";

    //手机号正则
    private static final String PHONE_NUMBER = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    public static boolean verifyCompanyId(String companyId){
        return companyId.matches(COMPANY_ID_VERIFY);
    }

    public static boolean verifyPhoneNumber(String companyId){
        return companyId.matches(PHONE_NUMBER);
    }
}