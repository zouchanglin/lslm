package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.VO.PartInfoOther;
import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.enums.SexEnum;
import edu.xpu.buckmoo.service.PageToPartInfoVO;
import edu.xpu.buckmoo.service.PartCategoryService;
import edu.xpu.buckmoo.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageToPartInfoVOImpl implements PageToPartInfoVO {
    private final PartCategoryService partCategoryService;

    private final UserInfoService userInfoService;

    public PageToPartInfoVOImpl(PartCategoryService partCategoryService, UserInfoService userInfoService) {
        this.partCategoryService = partCategoryService;
        this.userInfoService = userInfoService;
    }

    @Override
    public PartInfoVO partPageToPartInfoVO(Page<PartInfo> partInfoPage) {
        PartInfoVO partInfoVO = new PartInfoVO();
        List<PartInfoOther> partInfoOthers = new ArrayList<>();
        for(PartInfo partInfo : partInfoPage.getContent()){
            PartInfoOther partInfoOther = new PartInfoOther();

            BeanUtils.copyProperties(partInfo, partInfoOther);

            //性别
            Integer employSex = partInfo.getEmploySex();
            if(employSex.equals(SexEnum.MAN.getCode())){
                partInfoOther.setEmploySexStr(SexEnum.MAN.getMessage());
            }else if(employSex.equals(SexEnum.WOMAN.getCode())){
                partInfoOther.setEmploySexStr(SexEnum.WOMAN.getMessage());
            }else{
                partInfoOther.setEmploySexStr(SexEnum.OTHER.getMessage());
            }

            //分类
            PartCategory partCategory = partCategoryService.findById(partInfo.getPartCategory());
            partInfoOther.setPartCategoryStr(partCategory.getCategoryName());

            //微信用户名(发布者)
            String userNameCreate = userInfoService.findById(partInfo.getPartCreator()).getUserName();
            partInfoOther.setPartCreatorStr(userNameCreate);

            if(partInfo.getPartStatus() - PartTimeStatusEnum.TAKE_ORDER.getCode() >= 0){
                //微信用户名(接手者)
                UserInfo userEmploy = userInfoService.findById(partInfo.getPartEmploy());
                partInfoOther.setPartEmployStr(userEmploy.getUserName());

                //接手者手机号码
                partInfoOther.setPartEmployPhone(userEmploy.getUserPhone());
            }else{
                partInfoOther.setPartEmployStr("暂时无人接单");
                //接手者手机号码
                partInfoOther.setPartEmployPhone("");
            }


            //状态
            PartTimeStatusEnum oneByStatus = PartTimeStatusEnum.getOneByStatus(partInfo.getPartStatus());
            assert oneByStatus != null;
            partInfoOther.setPartStatusStr(oneByStatus.getMessage());

            partInfoOthers.add(partInfoOther);
        }
        partInfoVO.setPartInfoList(partInfoOthers);
        partInfoVO.setPageCount(partInfoPage.getTotalPages());
        return partInfoVO;
    }
}
