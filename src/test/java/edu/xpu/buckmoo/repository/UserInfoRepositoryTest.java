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
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(KeyUtil.genUniqueKey());
        userInfo.setOpenId(KeyUtil.genUniqueKey());
        userInfo.setUserName("Test Name");
        userInfo.setUserSex(1);
        userInfo.setUserIcon("http://www.png");
        userInfo.setUserCity("陕西省西安市");
        userInfo.setUserGrade(0);
        userInfo.setUserPhone("15278780512");
        assertNotNull(repository.save(userInfo));
    }


    @Test
    public void findByOpenId(){
        UserInfo byId = repository.findByOpenId("1561532064367550376");
        assertNotNull(byId);
    }

}