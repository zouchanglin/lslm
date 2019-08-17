package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.CompanyOrder;
import edu.xpu.buckmoo.enums.ActivityStatusEnum;
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
public class CompanyOrderRepositoryTest {
    @Autowired
    private CompanyOrderRepository companyOrderRepository;


    @Test
    public void save(){
        CompanyOrder companyOrder = new CompanyOrder();
        companyOrder.setOrderId(KeyUtil.genUniqueKey());
        companyOrder.setActivityStatus(ActivityStatusEnum.NEW.getCode());
        companyOrder.setOrderActivity("");
        companyOrder.setOrderMoney(new BigDecimal(0.01f));
        companyOrder.setCreateTime(System.currentTimeMillis());
        companyOrder.setUpdateTime(System.currentTimeMillis());

        CompanyOrder save = companyOrderRepository.save(companyOrder);
        assertNotNull(save);
        log.info("[CompanyOrderRepositoryTest] save={}", save);
    }
}