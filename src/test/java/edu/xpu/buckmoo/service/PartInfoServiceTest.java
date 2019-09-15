package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PartInfoServiceTest {
    @Autowired
    private PartInfoService partInfoService;
    @Test
    public void listByCategoryAndStatus() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<PartInfo> partInfoPage = partInfoService.listByCategoryAndStatus(2, PartTimeStatusEnum.PASS_PAY.getCode(), pageRequest);
        log.info("[PartInfoServiceTest] partInfoPage={}", partInfoPage.getContent());
    }
}