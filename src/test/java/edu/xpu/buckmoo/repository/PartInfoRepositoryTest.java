package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        partInfo.setPartName("兼职名称_01");
        partInfo.setPartCategory(0);
        partInfo.setPartAddress("兼职地点_01");
        partInfo.setPartOverview("兼职详细描述信息");
        partInfo.setPartStart(System.currentTimeMillis());
        partInfo.setPartEnd(System.currentTimeMillis());
        partInfo.setPartTime("兼职时间补充");
        partInfo.setPartMoney(new BigDecimal(0.01));
        partInfo.setPartCreator("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
        assertNotNull(repository.save(partInfo));
    }

    @Test
    public void find_01(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<PartInfo> category = repository.findAllByPartCategory(3, pageRequest);
        List<PartInfo> content = category.getContent();
        for(PartInfo partInfo: content){
            System.out.println(partInfo);
        }
    }

    @Test
    public void find_02(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<PartInfo> category = repository.findAllByPartCreator("oxrwq0xrKKyqiAGE8O9TM3L1yaQY", pageRequest);
        List<PartInfo> content = category.getContent();
        for(PartInfo partInfo: content){
            System.out.println(partInfo);
        }
    }

    @Test
    public void find_03(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<PartInfo> category = repository.findAllByPartCreatorAndPartStatus("oxrwq0xrKKyqiAGE8O9TM3L1yaQY", 1, pageRequest);
        List<PartInfo> content = category.getContent();
        for(PartInfo partInfo: content){
            System.out.println(partInfo);
        }
    }

    @Test
    public void find_04(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<PartInfo> category = repository.findAllByPartCategoryAndPartStatus(2, 1, pageRequest);
        List<PartInfo> content = category.getContent();
        for(PartInfo partInfo: content){
            System.out.println(partInfo);
        }
    }
}