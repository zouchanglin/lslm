package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.enums.PayStatusEnum;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MemberOrderRepositoryTest {
    @Autowired
    private MemberOrderRepository memberOrderRepository;

    @Test
    public void save(){
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setPayStatus(PayStatusEnum.NOT_PAY.getCode());
        memberOrder.setOrderId(KeyUtil.genUniqueKey());
        memberOrder.setOrderCompany(KeyUtil.genUniqueKey());
        memberOrder.setOrderMoney(new BigDecimal(0.01));
        MemberOrder save = memberOrderRepository.save(memberOrder);
        assertNotNull(save);
        log.info("[MemberOrderRepositoryTest] save={}",save);
    }
}