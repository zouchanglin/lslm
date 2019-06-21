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
    private String mpAppId;
    private String mpAppSecret;
}
