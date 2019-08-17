package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.PartCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PartCategoryRepositoryTest {
    @Autowired
    private PartCategoryRepository partCategoryRepository;

    @Test
    public void save(){
        PartCategory partCategory = new PartCategory();
        partCategory.setCategoryName("辅导");
        partCategory.setCreateTime(System.currentTimeMillis());
        partCategory.setUpdateTime(System.currentTimeMillis());

        PartCategory save = partCategoryRepository.save(partCategory);
        assertNotNull(save);
        log.info("[PartCategoryRepositoryTest] save={}", save);
    }
}