package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.enums.CollectionOrderTypeEnum;
import edu.xpu.buckmoo.enums.PayStatusEnum;
import edu.xpu.buckmoo.repository.CollectionOrderRepository;
import edu.xpu.buckmoo.service.MemberService;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private CollectionOrderRepository collectionOrderRepository;

    public MemberServiceImpl(CollectionOrderRepository collectionOrderRepository) {
        this.collectionOrderRepository = collectionOrderRepository;
    }

    @Override
    public CollectionOrder addNewMember(Integer memberLevel, BigDecimal money, String orderOpenid){
        String key = KeyUtil.genUniqueKey();

        CollectionOrder collectionOrder = new CollectionOrder();
        collectionOrder.setOrderMoney(money);
        collectionOrder.setOrderOpenid(orderOpenid);
        collectionOrder.setOrderName(orderOpenid + " 成为会员");
        collectionOrder.setOrderId(key);
        collectionOrder.setOrderPayStatus(PayStatusEnum.NOT_PAY.getCode());
        collectionOrder.setOrderType(CollectionOrderTypeEnum.USER_MEMBER_PAY.getCode());

        CollectionOrder saveResult = collectionOrderRepository.save(collectionOrder);
        log.info("[MemberServiceImpl] saveResult={}", saveResult);
        return saveResult;
    }
}
