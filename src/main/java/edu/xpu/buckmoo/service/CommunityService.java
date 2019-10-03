package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CommunityInfo;

public interface CommunityService {
    CommunityInfo addNewCommunity(CommunityInfo communityInfo);

    CommunityInfo findByOpenid(String openid);
}
