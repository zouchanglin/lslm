package edu.xpu.buckmoo.service.transform;

import edu.xpu.buckmoo.VO.CommunityInfoVO;
import edu.xpu.buckmoo.dataobject.CommunityInfo;

public interface CommunityToVO {
    CommunityInfoVO communityToVO(CommunityInfo communityInfo);
}
