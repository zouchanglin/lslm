package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.enums.UserSexEnum;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PartInfoRepositoryTest {
    @Autowired
    private PartInfoRepository partInfoRepository;

    @Test
    public void save(){
        PartInfo partInfo = new PartInfo();
        partInfo.setPartId(KeyUtil.genUniqueKey());
        partInfo.setPartName("兼职名称");
        partInfo.setPartStatus(PartTimeStatusEnum.NO_PAY.getCode());
        partInfo.setPartCreator(KeyUtil.genUniqueKey());
        partInfo.setPartStart(System.currentTimeMillis());
        partInfo.setPartEnd(System.currentTimeMillis());
        partInfo.setPartAddress("兼职地址");
        partInfo.setEmploySex(UserSexEnum.OTHER.getCode());
        partInfo.setPartMoney(new BigDecimal(0.01));
        partInfo.setPartMoneyShow(new BigDecimal(0.01));
        partInfo.setPartTime("兼职备注信息");
        partInfo.setPartOverview("兼职描述信息");

        PartInfo save = partInfoRepository.save(partInfo);
        assertNotNull(save);
        log.info("[PartInfoRepositoryTest] save={}", save);
    }
}