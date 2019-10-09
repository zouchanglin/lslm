package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.CollectionOrder;

import java.math.BigDecimal;

public interface MemberService {
    CollectionOrder addNewMember(Integer memberLevel, BigDecimal money, String orderName);
}
