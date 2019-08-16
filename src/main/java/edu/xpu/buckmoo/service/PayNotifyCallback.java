package edu.xpu.buckmoo.service;

public interface PayNotifyCallback {
    /**
     * 各种回调
     * @param notifyData 微信官方返回的数据
     * @param mode 支付回调模式 {@link edu.xpu.buckmoo.enums.WeChatPayNotifyEnum}
     */
    void payNotify(String notifyData, Integer mode);
}
