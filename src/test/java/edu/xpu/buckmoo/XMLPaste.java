package edu.xpu.buckmoo;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class XMLPaste {
    @Test
    public void demo(){
        String s = "<xml>\n" +
                "\t<appid><![CDATA[wxb39b42f5fd93c167]]></appid>\n" +
                "\t<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "\t<cash_fee><![CDATA[1]]></cash_fee>\n" +
                "\t<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "\t<is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "\t<mch_id><![CDATA[1545797881]]></mch_id>\n" +
                "\t<nonce_str><![CDATA[k9aLTeA4QwEhk3cB]]></nonce_str>\n" +
                "\t<openid><![CDATA[oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk]]></openid>\n" +
                "\t<out_trade_no><![CDATA[1566019583984383428]]></out_trade_no>\n" +
                "\t<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "\t<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "\t<sign><![CDATA[9879B2CDF0CE2E93EB1A12E1928D49EE]]></sign>\n" +
                "\t<time_end><![CDATA[20190817155832]]></time_end>\n" +
                "\t<total_fee>1</total_fee>\n" +
                "\t<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "\t<transaction_id><![CDATA[4200000387201908179183467465]]></transaction_id>\n" +
                "</xml>";

        Document document= null;//xmlStr为上图格式的字符串
        try {
            document = DocumentHelper.parseText(s);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assert document != null;
        Node out_trade_no = document.selectSingleNode("//out_trade_no");
        log.info("解析结果={}", out_trade_no.getText());
    }

    @Test
    public void s() {
        System.out.println(-1>>>1&2
        );
    }
}
