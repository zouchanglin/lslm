package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.PartCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartCategoryRepositoryTest {
    @Autowired
    private PartCategoryRepository repository;

    @Test
    public void save(){

    }
}