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
        partInfo.setPartName("XXXXXXXXXXXX");
        partInfo.setPartCategory(0);
        partInfo.setPartAddress("西安工程大学临潼校区");
        partInfo.setPartOverview("这是详细描述信息");
        partInfo.setPartStart(new Date());
        partInfo.setPartEnd(new Date());
        partInfo.setPartTime("对兼职时间的一个补充");
        partInfo.setPartMoney(new BigDecimal(40.00));
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


    @Test
    public void save2(){
        Optional<PartInfo> byId = repository.findById("111111111111111");
        Date startTime = byId.get().getPartStart();
        Date endTime = byId.get().getPartEnd();
        System.out.println(startTime);
        System.out.println(endTime);
    }
}