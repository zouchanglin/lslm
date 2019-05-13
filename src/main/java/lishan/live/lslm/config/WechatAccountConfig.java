package lishan.live.lslm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信账户配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String myAppId;
    private String myAppSecret;

    private String mchId;
    private String mchKey;
    private String keyPath;
}
