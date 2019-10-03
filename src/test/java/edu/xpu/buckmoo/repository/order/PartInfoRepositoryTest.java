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
        for (int i = 0; i < 2000; i++) {
            PartInfo partInfo = new PartInfo();
            partInfo.setPartId(KeyUtil.genUniqueKey());
            partInfo.setPartName("兼职名称" + i);
            if(i%2 == 0){
                partInfo.setPartCategory(1);
            }else if(i%3 == 0){
                partInfo.setPartCategory(2);
            }else if(i%5 == 0){
                partInfo.setPartCategory(3);
            }else if(i%7 == 0){
                partInfo.setPartCategory(4);
            }else {
                partInfo.setPartCategory(5);
            }
            partInfo.setPartStatus(PartTimeStatusEnum.PASS_PAY.getCode());
            //partInfo.setPartCreator(KeyUtil.genUniqueKey());
            partInfo.setPartStart(System.currentTimeMillis());
            partInfo.setPartEnd(System.currentTimeMillis());
            partInfo.setPartAddress("兼职地址" + i);
            partInfo.setEmploySex(UserSexEnum.OTHER.getCode());
            partInfo.setPartMoney(new BigDecimal(15.00));
            partInfo.setPartMoneyShow(new BigDecimal(10.00));
            partInfo.setPartTime("兼职备注信息" + i);
            partInfo.setPartOverview("兼职描述信息" + i);
            if(i % 2 == 0){
                partInfo.setPartCreator("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
            }else{
                partInfo.setPartCreator("oxrwq0xrKKyqiAGE8O9TM3L1yaQY");
            }

            PartInfo save = partInfoRepository.save(partInfo);
            assertNotNull(save);
            log.info("[PartInfoRepositoryTest] save={}", save);
        }
    }
}