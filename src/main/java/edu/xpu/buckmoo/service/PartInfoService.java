package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.PartInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PartInfoService {
    PartInfo findOneById(String partId);

    Page<PartInfo> listByCategory(Integer category, Pageable pageable);

    Page<PartInfo> listByCategoryAndStatus(Integer category, Integer status, Pageable pageable);

    PartInfo addOnePartTime(PartInfo partInfo);

    Page<PartInfo> listByUserCreate(String openid, PageRequest pageRequest, Integer status);

    Page<PartInfo> listByUserAccept(String openid, PageRequest pageRequest, Integer status);
}
