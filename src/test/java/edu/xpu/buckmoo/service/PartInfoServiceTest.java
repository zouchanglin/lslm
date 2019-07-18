package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PartInfoServiceTest {
    @Autowired
    private PartInfoService partInfoService;

    @Test
    public void findOneById() {
    }

    @Test
    public void listByCategory() {
    }

    @Test
    public void listByCategoryAndStatus() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<PartInfo> partInfos = partInfoService.listByCategoryAndStatus(2, PartTimeStatusEnum.PASS_PAY.getCode(), pageRequest);
        List<PartInfo> content = partInfos.getContent();
        for(PartInfo partInfo: content){
            System.out.println(partInfo);
        }
    }

    @Test
    public void addOnePartTime() {
    }
}