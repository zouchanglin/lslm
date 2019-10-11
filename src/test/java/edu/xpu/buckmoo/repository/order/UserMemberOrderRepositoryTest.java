package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.UserMemberOrder;
import edu.xpu.buckmoo.utils.KeyUtil;
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
public class UserMemberOrderRepositoryTest {
    @Autowired
    private UserMemberOrderRepository userMemberOrderRepository;


    @Test
    public void save(){
        UserMemberOrder userMemberOrder = new UserMemberOrder();
        userMemberOrder.setOrderId(KeyUtil.genUniqueKey());
        userMemberOrder.setOpenid("111111");
        userMemberOrder.setMemberType(2);
        userMemberOrder.setOrderCollection("111111");
        UserMemberOrder save = userMemberOrderRepository.save(userMemberOrder);
        assertNotNull(save);
    }
}