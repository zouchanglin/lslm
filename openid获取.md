
## 首先需要一个内网穿透
http://tim.natapp1.cc

## 设置微信开发域名

内网穿透 + 项目静态资源访问(就是那个需要下载的txt文件，确保微信服务器能够访问到)

![](https://s2.ax1x.com/2019/06/21/Vz6MqJ.png)

## 用户授权，获取code

看这里的文档：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842

**用户同意授权，获取code**

替换APPID、REDIRECT_URI、SCOPE(授权类型)

```
https://open.weixin.qq.com/connect/oauth2/authorize?appid=&redirect_uri=http://tim.natapp1.cc/buckmoo/wechat/auth&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
```

此时，SpringBoot项目中这样写一个请求地址：

```java
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}", code);
    }
}
```

然后访问上面的地址：

![](https://s2.ax1x.com/2019/06/21/ZSFb9J.png)

如果直接使用浏览器访问当然不行，必须使用微信手机客户端打开这个链接，此时的控制台信息：

```
2019-06-21 15:41:08.358  INFO 1879 --- [nio-8080-exec-2] e.x.buckmoo.controller.WeChatController  : 进入auth方法
2019-06-21 15:41:08.359  INFO 1879 --- [nio-8080-exec-2] e.x.buckmoo.controller.WeChatController  : code=081UmDV00jqzzI1iKHV00ISkV00UmDVG
```

## 获取access_token

**通过code换取网页授权access_token**

替换APPID、SECRET

```
https://api.weixin.qq.com/sns/oauth2/access_token?appid=&secret=&code=CODE&grant_type=authorization_code
```

在工程中：

```java
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}", code);
        
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=&secret=&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        log.info("response={}", forObject);
    }
}
```

此时打印出的字符串中就包含了access_token信息

```json
{
    "access_token":"22_vk1E5EfLqQ7h3lglDYHO6SZicC_bsvfx39_8uC-Zrad7C5NDG1N7lZvBXueQ19Y6rPLcRkTkCW99mKUpVbF31jKwSTH2Q_vfa2M9PyMq7dY",
    "expires_in":7200,
    "refresh_token":"22_cQU4DCsbNtDp4QoiVIKbDsjgka97OJu4gwBdvuJjdo8FF7w0s10ePYDtL28K7sZriX33fUvWbbV2Rbss4DGAOu19LgNCEMcWA3U1IVNm3fE",
    "openid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
    "scope":"snsapi_base"
}
```

如果我们把`scope=snsapi_base`设置为`scope=snsapi_userinfo`，那么可以拿到用户信息：

```java
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=&secret=&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        log.info("response={}", forObject);

        String openId = JSONObject.parseObject(forObject).getString("openid");
        String accessToken = JSONObject.parseObject(forObject).getString("access_token");
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
        RestTemplate restTemplateUserInfo = new RestTemplate();
        String userInfo = restTemplateUserInfo.getForObject(userInfoUrl, String.class);
        log.info("userInfo={}", userInfo);

    }
}
```

返回的userInfo如下：

![](https://s2.ax1x.com/2019/06/21/ZSQFne.png)

```json
{
	"openid": "oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
	"nickname": "Tim",
	"sex": 1,
	"language": "zh_CN",
	"city": "",
	"province": "",
	"country": "æ³½è¥¿å²",
	"headimgurl": "http:\/\/thirdwx.qlogo.cn\/mmopen\/vi_32\/bxVEQxwmOLibgHtYurJxvW0yicXLVcTCUiaDQDqibEyoIKwS7ZRdOsZL02RibF79vdNt6cFEYU1v53r1plygOAL60hw\/132",
	"privilege": []
}
```

## 使用SDK的方式

https://github.com/search?q=weixin-java-tools

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>
  <version>3.4.0</version>
</dependency>
```

首先配置一下application.yml

```
wechat:
  mpAppId: wxb39b42f5fd93c167
  mpAppSecret: 3d63c96f84e4587baa5e4a6f85d1a515
```

WechatAccountConfig.java

```java
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;
}
```

WeChatMpConfig.java

```java
@Component
public class WeChatMpConfig {
    @Autowired
    private WechatAccountConfig accountConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(accountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(accountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }
}
```

WeChatController.java

```java
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //1、配置
        //2、调用方法
        String url = "http://tim.natapp1.cc/buckmoo/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new BuckMooException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
```

这个时候只要请求：

```
http://tim.natapp1.cc/buckmoo/wechat/authorize?returnUrl=https://www.baidu.com
```

里面跳转到百度的首页了！设置returnUrl这个参数就可以跳转到自己想要的页面！

![](https://s2.ax1x.com/2019/06/21/ZS3kBF.png)