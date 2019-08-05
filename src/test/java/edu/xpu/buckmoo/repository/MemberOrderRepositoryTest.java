package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.MemberOrder;
import edu.xpu.buckmoo.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberOrderRepositoryTest {
   @Autowired
   private MemberOrderRepository memberOrderRepository;

   @Test
   public void testSave(){
       MemberOrder memberOrder = new MemberOrder();
       memberOrder.setOrderId(KeyUtil.genUniqueKey());
       memberOrder.setOrderMoney(new BigDecimal(350));
       memberOrder.setOrderCompany("1560234861488151073");
       memberOrder.setPayStatus(0);

       MemberOrder saveResult = memberOrderRepository.save(memberOrder);
       assertNotNull(saveResult);
   }
}