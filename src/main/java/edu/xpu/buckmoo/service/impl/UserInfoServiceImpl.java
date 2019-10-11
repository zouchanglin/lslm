package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.UserInfo;
import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.enums.MemberLevelEnum;
import edu.xpu.buckmoo.repository.UserInfoRepository;
import edu.xpu.buckmoo.service.UserInfoService;
import edu.xpu.buckmoo.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tim
 * @version 1.0
 * @className UserInfoServiceImpl
 * @description
 * @date 2019-06-21 21:51
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userRep;

    public UserInfoServiceImpl(UserInfoRepository userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserInfo updateUserToMember(Integer memberLevel, String openid) {
        Optional<UserInfo> findResult = userRep.findById(openid);
        if(!findResult.isPresent()) throw new RuntimeException("参数错误(用户不存在)");
        MemberLevelEnum levelEnum = EnumUtil.getByCode(memberLevel, MemberLevelEnum.class);
        if(levelEnum == null) throw new RuntimeException("参数错误(会员等级信息不存在)");
        Integer levelEnumCode = levelEnum.getCode();

        UserInfo userInfo = findResult.get();
        userInfo.setUserMember(levelEnumCode);

        //根据等级确定到期时间
        if(MemberLevelEnum.ONE_LEVEL.getCode().equals(memberLevel)){
            userInfo.setMemberPast(System.currentTimeMillis() + 30L * 24L * 3600L * 1000L);
        }else if(MemberLevelEnum.TWO_LEVEL.getCode().equals(memberLevel)){
            userInfo.setMemberPast(System.currentTimeMillis() + 30L * 12 * 24L * 3600L * 1000L);
        }else if(MemberLevelEnum.THREE_LEVEL.getCode().equals(memberLevel)){
            userInfo.setMemberPast(System.currentTimeMillis() + Long.MAX_VALUE);
        }

        UserInfo saveInfoResult = userRep.save(userInfo);
        log.info("[UserInfoServiceImpl] saveInfoResult={}", saveInfoResult);
        return saveInfoResult;
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        Optional<UserInfo> findResult = userRep.findById(userInfo.getOpenId());
        if(!findResult.isPresent()){
            //如果不存在
            userInfo.setCreateTime(System.currentTimeMillis());
            userInfo.setUserGrade(0);
            userInfo.setUserMember(MemberLevelEnum.COMMON.getCode());
            userInfo.setCompanyId("");
            userInfo.setUserPhone("");
            userInfo.setMemberPast(System.currentTimeMillis());
        }else{
            //排除其他信息，先弄过来
            UserInfo info = findResult.get();
            userInfo.setCreateTime(info.getCreateTime());
            userInfo.setUserPhone(info.getUserPhone());
            userInfo.setCompanyId(info.getCompanyId());
            userInfo.setUserMember(info.getUserMember());
            userInfo.setUserGrade(info.getUserGrade());
            if(!MemberLevelEnum.COMMON.getCode().equals(userInfo.getUserMember())){
                userInfo.setMemberPast(info.getMemberPast());
            }else{
                userInfo.setMemberPast(System.currentTimeMillis());
            }
        }
        userInfo.setUpdateTime(System.currentTimeMillis());
        return userRep.save(userInfo);
    }

    @Override
    public UserInfo findById(String openId) {
        return userRep.findById(openId).orElse(null);
    }

    @Override
    public Integer userCount() {
        return userRep.findAll().size();
    }
}
