package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CollectionOrder;


public interface MemberService {
    CollectionOrder addNewUserMember(Integer memberLevel, String openid);
}
