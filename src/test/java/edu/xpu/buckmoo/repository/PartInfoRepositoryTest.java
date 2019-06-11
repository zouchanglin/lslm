package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartInfoRepositoryTest {
    @Autowired
    private PartInfoRepository repository;


    @Test
    public void save(){
        PartInfo partInfo = new PartInfo();
        partInfo.setPartId(KeyUtil.genUniqueKey());
        partInfo.setPartName("周三代课");
        partInfo.setPartCategory(0);
        partInfo.setPartAddress("西安工程大学临潼校区");
        partInfo.setPartOverview("这是详细描述信息");
        partInfo.setPartStart(new Date());
        partInfo.setPartEnd(new Date());
        partInfo.setPartTime("对兼职时间的一个补充");
        partInfo.setPartMoney(new BigDecimal(40.00));
        assertNotNull(repository.save(partInfo));
    }
}