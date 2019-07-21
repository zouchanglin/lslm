package edu.xpu.buckmoo.service.impl;

import com.lly835.bestpay.model.PayResponse;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayServiceImplTest {
    @Autowired
    private PayService payService;

    @Autowired
    private PartInfoService partInfoService;

    @Test
    public void partPay() {
        PartInfo oneById = partInfoService.findOneById("1963348810541158995");
        PayResponse payResponse = payService.partPay(oneById);
    }

    @Test
    public void refund(){
        payService.refund(partInfoService.findOneById("1563528411222170787"));
    }
}