package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.enums.CollectionOrderTypeEnum;
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
public class CollectionOrderRepositoryTest {
    @Autowired
    private CollectionOrderRepository collectionOrderRepository;

    @Test
    public void save() {
        CollectionOrder collectionOrder = new CollectionOrder();
        collectionOrder.setOrderId(KeyUtil.genUniqueKey());
        collectionOrder.setOrderMoney(new BigDecimal(0.01f));
        collectionOrder.setOrderName("订单名称");
        collectionOrder.setOrderOpenid(KeyUtil.genUniqueKey());
        collectionOrder.setOrderType(CollectionOrderTypeEnum.COMPANY_MEMBER_PAY.getCode());
        CollectionOrder save = collectionOrderRepository.save(collectionOrder);
        assertNotNull(save);
        log.info("[CollectionOrderRepositoryTest] save={}", save);
    }

}
