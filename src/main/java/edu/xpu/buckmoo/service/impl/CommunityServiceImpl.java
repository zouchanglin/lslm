package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.CommunityInfo;
import edu.xpu.buckmoo.repository.CommunityRepository;
import edu.xpu.buckmoo.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public CommunityInfo addNewCommunity(CommunityInfo communityInfo) {
        CommunityInfo firstByOpenid = communityRepository.findFirstByOpenid(communityInfo.getOpenid());
        //已经存在，不能注册了
        if(firstByOpenid != null) {
            return null;
        }
        return communityRepository.save(communityInfo);
    }

    @Override
    public CommunityInfo findByOpenid(String openid) {
        return communityRepository.findFirstByOpenid(openid);
    }
}
