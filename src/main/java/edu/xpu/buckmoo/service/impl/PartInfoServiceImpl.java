package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.repository.PartInfoRepository;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.EnumUtil;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tim
 * @version 1.0
 * @className PartInfoService
 * @description
 * @date 2019-06-22 00:38
 */
@Service
public class PartInfoServiceImpl implements PartInfoService {
    private final PartInfoRepository partRep;
    private final UserInfoService userInfoService;

    public PartInfoServiceImpl(PartInfoRepository partRep, UserInfoService userInfoService) {
        this.partRep = partRep;
        this.userInfoService = userInfoService;
    }

    @Override
    public PartInfo findOneById(String partId) {
        return partRep.findById(partId).orElse(null);
    }

    @Override
    public Page<PartInfo> listByCategory(Integer category, Pageable pageable) {
        return partRep.findAllByPartCategory(category, pageable);
    }

    @Override
    public Page<PartInfo> listByCategoryAndStatus(Integer category, Integer status, Pageable pageable) {
        return partRep.findAllByPartCategoryAndPartStatus(category, status, pageable);
    }

    @Override
    public PartInfo addOnePartTime(PartInfo partInfo) {
        return partRep.save(partInfo);
    }

    @Override
    public Page<PartInfo> listByUserCreate(String openid, PageRequest pageRequest, Integer status) {
        return partRep.findAllByPartCreatorAndPartStatus(openid, status, pageRequest);
    }

    @Override
    public Page<PartInfo> listByUserAccept(String openid, PageRequest pageRequest, Integer status) {
        return partRep.findAllByPartEmployAndPartStatus(openid, status, pageRequest);
    }


    @Override
    public PartInfo modifyPartStatus(String orderId, Integer code) {
        PartInfo partInfo = partRep.findById(orderId).orElse(null);
        if(partInfo == null) throw new BuckMooException(ResultEnum.PART_NOT_EXIT);

        PartTimeStatusEnum partStatusEnum = EnumUtil.getByCode(code, PartTimeStatusEnum.class);
        if(partStatusEnum == null) throw new BuckMooException(ResultEnum.ENUM_NOT_EXITS);

        partInfo.setPartStatus(partStatusEnum.getCode());
        return partRep.save(partInfo);
    }

    @Override
    public Page<PartInfo> userAllCreate(String openid, PageRequest pageRequest) {
        return partRep.findAllByPartCreator(openid, pageRequest);
    }

    @Override
    public Page<PartInfo> userAllAccept(String openid, PageRequest pageRequest) {
        return partRep.findAllByPartEmploy(openid, pageRequest);
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
        PartInfo findRet = partRep.findById(partId).orElse(null);
        if(findRet == null) throw new BuckMooException(ResultEnum.PART_NOT_EXIT);

        if(!findRet.getPartCreator().equals(openid))
            throw new BuckMooException(ResultEnum.PARAM_ERROR);

        findRet.setPartStatus(PartTimeStatusEnum.FINISH_CREATE.getCode());
        return partRep.save(findRet);
    }
}
