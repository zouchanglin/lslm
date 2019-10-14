package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.dataobject.order.UserMemberOrder;
import edu.xpu.buckmoo.enums.CollectionOrderTypeEnum;
import edu.xpu.buckmoo.enums.PayStatusEnum;
import edu.xpu.buckmoo.repository.CollectionOrderRepository;
import edu.xpu.buckmoo.repository.config.SystemConfigRepository;
import edu.xpu.buckmoo.repository.order.UserMemberOrderRepository;
import edu.xpu.buckmoo.service.MemberService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final CollectionOrderRepository collectionOrderRepository;
    private final SystemConfigRepository systemConfigRepository;
    private final UserMemberOrderRepository userMemberOrderRepository;
    private final UserInfoService userInfoService;

    private SystemConfig member_forever_user;
    private SystemConfig member_year_user;
    private SystemConfig member_month_user;

    public MemberServiceImpl(CollectionOrderRepository collectionOrderRepository,
                             SystemConfigRepository systemConfigRepository,
                             UserMemberOrderRepository userMemberOrderRepository,
                             UserInfoService userInfoService) {
        this.collectionOrderRepository = collectionOrderRepository;
        this.systemConfigRepository = systemConfigRepository;
        this.userMemberOrderRepository = userMemberOrderRepository;
        this.userInfoService = userInfoService;
    }

    @Override
    public CollectionOrder changeUserMember(int memberLevel, String openid) {
        //初始化系统参数
        initSystemParam();

        UserInfo userInfo = userInfoService.findById(openid);
        int userMember = userInfo.getUserMember();
        if(memberLevel <= userMember) throw new RuntimeException("会员比之前的等级更低，升级参数错误");

        SystemConfig memberForever;
        //开通会员
        if(memberLevel == 3) memberForever = member_forever_user;
        else if(memberLevel == 2) memberForever = member_year_user;
        else if(memberLevel == 1) memberForever = member_month_user;
        else {
            log.error("[UserPayController] 参数错误");
            throw new RuntimeException("参数传递错误");
        }

        SystemConfig memberForeverOld;
        //那是最高会员等级，所以无需判断
        if(userMember == 2) memberForeverOld = member_year_user;
        else if(userMember == 1) memberForeverOld = member_month_user;
        else{
            log.error("[UserPayController] 参数错误");
            throw new RuntimeException("参数传递错误");
        }


        String key = KeyUtil.genUniqueKey();
        CollectionOrder collectionOrder = new CollectionOrder();

        //减去之前的会员支付费用
        collectionOrder.setOrderMoney(memberForever.getParamsValue().subtract(memberForeverOld.getParamsValue()));
        collectionOrder.setOrderOpenid(openid);
        collectionOrder.setOrderName("用户成为会员[会员升级]");
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

        return null;
    }

    @Override
    public CollectionOrder addNewUserMember(int memberLevel, String openid) {
        //初始化系统参数
        initSystemParam();


        SystemConfig memberForever;
        //开通会员
        if(memberLevel == 3)  memberForever = member_forever_user;
        else if(memberLevel == 2) memberForever = member_year_user;
        else if(memberLevel == 1) memberForever = member_month_user;
        else{
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

    //初始化系统参数
    private void initSystemParam(){
        member_forever_user = systemConfigRepository.findOneByParamsId("member_forever_user");
        member_year_user = systemConfigRepository.findOneByParamsId("member_year_user");
        member_month_user = systemConfigRepository.findOneByParamsId("member_month_user");
    }
}