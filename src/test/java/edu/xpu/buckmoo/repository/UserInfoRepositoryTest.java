package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.enums.SexEnum;
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
        userInfo.setOpenId("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
        userInfo.setUserAddress("Address");
        userInfo.setUserIcon("http://www.baidu.com/a.png");
        userInfo.setUserSex(SexEnum.MAN.getCode());
        userInfo.setCreateTime(System.currentTimeMillis());
        userInfo.setUpdateTime(System.currentTimeMillis());
        userInfo.setUserMember(MemberLevelEnum.COMMON.getCode());
    }


    @Test
    public void findByOpenId(){
        UserInfo byId = repository.findByOpenId("oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk");
        assertNotNull(byId);
    }

}