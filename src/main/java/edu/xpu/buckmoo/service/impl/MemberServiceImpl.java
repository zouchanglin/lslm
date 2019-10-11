package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.dataobject.order.UserMemberOrder;
import edu.xpu.buckmoo.enums.CollectionOrderTypeEnum;
import edu.xpu.buckmoo.enums.PayStatusEnum;
import edu.xpu.buckmoo.repository.CollectionOrderRepository;
import edu.xpu.buckmoo.repository.config.SystemConfigRepository;
import edu.xpu.buckmoo.repository.order.UserMemberOrderRepository;
import edu.xpu.buckmoo.service.MemberService;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final CollectionOrderRepository collectionOrderRepository;
    private final SystemConfigRepository systemConfigRepository;
    private final UserMemberOrderRepository userMemberOrderRepository;

    public MemberServiceImpl(CollectionOrderRepository collectionOrderRepository, SystemConfigRepository systemConfigRepository, UserMemberOrderRepository userMemberOrderRepository) {
        this.collectionOrderRepository = collectionOrderRepository;
        this.systemConfigRepository = systemConfigRepository;
        this.userMemberOrderRepository = userMemberOrderRepository;
    }

    @Override
    public CollectionOrder addNewUserMember(Integer memberLevel, String openid) {

        SystemConfig memberForever;
        //开通会员
        if(memberLevel == 3) {
            memberForever = systemConfigRepository.findOneByParamsId("member_forever_user");
        }else if(memberLevel == 2){
            memberForever = systemConfigRepository.findOneByParamsId("member_year_user");
        }else if(memberLevel == 1){
            memberForever = systemConfigRepository.findOneByParamsId("member_month_user");
        }else{
            log.error("[UserPayController] 参数错误");
            throw new RuntimeException("参数传递错误");
        }

        String key = KeyUtil.genUniqueKey();

        CollectionOrder collectionOrder = new CollectionOrder();
        collectionOrder.setOrderMoney(memberForever.getParamsValue());
        collectionOrder.setOrderOpenid(openid);
        collectionOrder.setOrderName("用户成为会员");
        collectionOrder.setOrderId(key);
        collectionOrder.setOrderPayStatus(PayStatusEnum.NOT_PAY.getCode());
        collectionOrder.setOrderType(CollectionOrderTypeEnum.USER_MEMBER_PAY.getCode());

        CollectionOrder saveResult = collectionOrderRepository.save(collectionOrder);

        UserMemberOrder userMemberOrder = new UserMemberOrder();
        userMemberOrder.setOrderCollection(key);
        userMemberOrder.setOpenid(openid);
        userMemberOrder.setMemberType(memberLevel);
        userMemberOrder.setOrderId(KeyUtil.genUniqueKey());
        UserMemberOrder saveMemberResult = userMemberOrderRepository.save(userMemberOrder);

        log.info("[MemberServiceImpl] saveMemberResult={}", saveMemberResult);
        log.info("[MemberServiceImpl] saveResult={}", saveResult);

        return saveResult;
    }
}