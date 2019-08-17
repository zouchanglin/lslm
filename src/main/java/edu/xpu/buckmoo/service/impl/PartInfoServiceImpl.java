package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CollectionOrder;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import edu.xpu.buckmoo.enums.CollectionOrderTypeEnum;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.enums.PayStatusEnum;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.CollectionOrderRepository;
import edu.xpu.buckmoo.repository.order.PartInfoRepository;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author tim
 * @version 1.2
 * @className PartInfoService
 * @description
 * @date 2019-08-17 00:38
 */
@Service
@Slf4j
public class PartInfoServiceImpl implements PartInfoService {
    private final PartInfoRepository partInfoRepository;

    @Autowired
    private CollectionOrderRepository collectionOrderRepository;

    public PartInfoServiceImpl(PartInfoRepository partRep) {
        this.partInfoRepository = partRep;
    }

    @Override
    public PartInfo findOneById(String partId) {
        return partInfoRepository.findById(partId).orElse(null);
    }

    @Override
    public Page<PartInfo> listByCategory(Integer category, Pageable pageable) {
        return partInfoRepository.findAllByPartCategory(category, pageable);
    }

    @Override
    public Page<PartInfo> listByCategoryAndStatus(Integer category, Integer status, Pageable pageable) {
        return partInfoRepository.findAllByPartCategoryAndPartStatus(category, status, pageable);
    }

    @Override
    public PartInfo addOnePartTime(PartInfo partInfo) {
        log.info("[PartInfoServiceImpl] partInfo={}", partInfo);

        //同时生成通用订单
        CollectionOrder collectionOrder = new CollectionOrder();
        collectionOrder.setOrderId(partInfo.getPartId());
        collectionOrder.setOrderType(CollectionOrderTypeEnum.USER_PART_PAY.getCode());
        collectionOrder.setOrderOpenid(partInfo.getPartCreator());
        collectionOrder.setOrderName("发布兼职信息付款");
        collectionOrder.setOrderPayStatus(PayStatusEnum.NOT_PAY.getCode());
        collectionOrder.setOrderMoney(partInfo.getPartMoney());

        CollectionOrder orderResult = collectionOrderRepository.save(collectionOrder);
        log.info("[PartInfoServiceImpl] orderResult={}", orderResult);
        return partInfoRepository.save(partInfo);
    }

    @Override
    public PartInfo modifyPartStatus(String orderId, Integer code) {
        PartInfo partInfo = partInfoRepository.findById(orderId).orElse(null);
        if(partInfo == null) throw new BuckMooException(ResultEnum.PART_NOT_EXIT);

        PartTimeStatusEnum partStatusEnum = EnumUtil.getByCode(code, PartTimeStatusEnum.class);
        if(partStatusEnum == null) throw new BuckMooException(ResultEnum.ENUM_NOT_EXITS);

        partInfo.setPartStatus(partStatusEnum.getCode());
        return partInfoRepository.save(partInfo);
    }

    @Override
    public Page<PartInfo> userAllCreate(String openid, PageRequest pageRequest) {
        return partInfoRepository.findAllByPartCreator(openid, pageRequest);
    }

    @Override
    public Page<PartInfo> userAllAccept(String openid, PageRequest pageRequest) {
        return partInfoRepository.findAllByPartEmploy(openid, pageRequest);
    }

    @Override
    public PartInfo acceptOnePart(String openid, String partId) {
        PartInfo findRet = findOneById(partId);
        if(findRet == null) throw new BuckMooException(ResultEnum.PART_NOT_EXIT);

        //检查兼职状态
        if(!findRet.getPartStatus().equals(PartTimeStatusEnum.PASS_PAY.getCode()))
            throw new BuckMooException(ResultEnum.PART_STATUS_ERROR);

        findRet.setPartStatus(PartTimeStatusEnum.TAKE_ORDER.getCode());
        findRet.setPartEmploy(openid);
        return addOnePartTime(findRet);
    }

    @Override
    public PartInfo finishOnePart(String openid, String partId) {
        PartInfo findRet = findOneById(partId);
        if(findRet == null) throw new BuckMooException(ResultEnum.PART_NOT_EXIT);

        //检查兼职状态
        if(!findRet.getPartStatus().equals(PartTimeStatusEnum.TAKE_ORDER.getCode()))
            throw new BuckMooException(ResultEnum.PART_STATUS_ERROR);

        findRet.setPartStatus(PartTimeStatusEnum.FINISH_ORDER.getCode());
        findRet.setPartEmploy(openid);
        return addOnePartTime(findRet);
    }

    @Override
    public PartInfo affirmFinishPart(String openid, String partId) {
        PartInfo findRet = partInfoRepository.findById(partId).orElse(null);
        if(findRet == null) throw new BuckMooException(ResultEnum.PART_NOT_EXIT);

        if(!findRet.getPartCreator().equals(openid))
            throw new BuckMooException(ResultEnum.PARAM_ERROR);

        findRet.setPartStatus(PartTimeStatusEnum.FINISH_CREATE.getCode());
        return partInfoRepository.save(findRet);
    }
}
