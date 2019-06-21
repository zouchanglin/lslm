package edu.xpu.buckmoo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tim
 * @version 1.0
 * @className WechatAccountConfig
 * @description
 * @date 2019-06-21 16:39
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * AppId
     */
    private String mpAppId;

    /**
     * 开发者秘钥
     */
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户秘钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String mchPath;
}
