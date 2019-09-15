package edu.xpu.buckmoo;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import edu.xpu.buckmoo.utils.KeyUtil;
import org.junit.Test;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
public class SendMessageTest {
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIAlvA2P2ekeha", "6KIoQD2X33V78JSYbp0wGLaUMUm18l");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "15291418231");
        request.putQueryParameter("SignName", "骊山鹿鸣");
        request.putQueryParameter("TemplateCode", "SMS_172603064");
        request.putQueryParameter("TemplateParam", "{\"code\":\"999999\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void genKey(){
        for (int i = 0; i < 5; i++) {
            System.out.println(KeyUtil.genVerifyKey());
        }
    }
}
