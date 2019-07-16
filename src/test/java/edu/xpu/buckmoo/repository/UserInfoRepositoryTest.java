package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository repository;

    @Test
    public void save(){
    }


    @Test
    public void findByOpenId(){
        UserInfo byId = repository.findByOpenId("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
        assertNotNull(byId);
    }

}