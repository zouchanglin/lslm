package edu.xpu.buckmoo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tim
 * @version 1.0
 * @className ProjectUrl
 * @description 对应application.yml的ProjectUrl中配置
 * @date 2019-06-23 09:36
 */
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {
    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthorize;

    /**
     * 微信开放平台授权Url
     */
    public String wechatOpenAuthorize;

    /**
     * 项目的url
     */
    public String buckmoo;

    /**
     * 图片存储路径
     */
    private String imgPath;
}
