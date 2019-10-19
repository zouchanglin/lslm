package edu.xpu.buckmoo.controller.community;

import edu.xpu.buckmoo.VO.ResultVO;
import edu.xpu.buckmoo.dataobject.CommunityInfo;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.service.CommunityService;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 社团信息控制器
 */
@RestController
@Slf4j
@RequestMapping("/community/info")
public class CommunityInfoController {
    private final CommunityService communityService;

    public CommunityInfoController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping("/register")
    public ResultVO register(@CookieValue(value = "openid", required = false) String openid,
            @ModelAttribute CommunityInfo communityInfo){
        if(openid == null) return ResultVOUtil.error(1, "请授权登录后使用");
        communityInfo.setOpenid(openid);
        communityInfo.setMember(MemberLevelEnum.COMMON.getCode());

        CommunityInfo addResult = communityService.addNewCommunity(communityInfo);
        if(addResult == null) return ResultVOUtil.error(2, "已经注册过");

        return ResultVOUtil.success(addResult);
    }

    @GetMapping("/show")
    public ResultVO show(@CookieValue(value = "openid", required = false) String openid){
        if(openid == null) return ResultVOUtil.error(1, "请授权登录后使用");
        CommunityInfo communityInfo = communityService.findByOpenid(openid);

        if(communityInfo == null) return ResultVOUtil.error(2, "未找到你注册的社团");
        return ResultVOUtil.success(communityInfo);
    }
}
