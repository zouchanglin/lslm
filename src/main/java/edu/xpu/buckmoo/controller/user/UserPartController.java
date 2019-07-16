package edu.xpu.buckmoo.controller.user;

import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.enums.PartTimeStatusEnum;
import edu.xpu.buckmoo.service.PartCategoryService;
import edu.xpu.buckmoo.service.PartInfoService;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tim
 * @version 1.0
 * @className UserPartController
 * @description
 * @date 2019-06-20 21:37
 */
@RestController
@Slf4j
@RequestMapping("/user/part")
public class UserPartController {
    @Autowired
    private PartInfoService partInfoService;

    @Autowired
    private PartCategoryService partCategoryService;

    @GetMapping("/list")
    public String getUserInfo(@RequestParam("pageindex") Integer pageindex){
        List<PartCategory> categoryList = partCategoryService.getAll();
        PageRequest pageRequest = PageRequest.of(pageindex, 8);

        List<PartInfoVO> ret = new ArrayList<>();
        for(PartCategory category: categoryList){
            PartInfoVO partInfoVO = new PartInfoVO();
                    List<PartInfo> content = partInfoService.listByCategoryAndStatus(category.getCategoryId(),
                    PartTimeStatusEnum.PASS_PAY.getCode(), pageRequest).getContent();
            partInfoVO.setPartCategory(category);
            partInfoVO.setPartInfoList(content);

            ret.add(partInfoVO);
        }
        return JsonUtil.toJson(ResultVOUtil.success(ret));
    }
}
