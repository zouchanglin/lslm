package edu.xpu.buckmoo.service;

public interface PayNotifyCallback {
    /**
     * 各种回调
     * @param notifyData 微信官方返回的数据
     */
    void payNotify(String notifyData);
}
