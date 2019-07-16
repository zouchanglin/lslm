package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.PartInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartInfoService {
    PartInfo findOneById(String partId);

    Page<PartInfo> listByCategory(Integer category, Pageable pageable);

    Page<PartInfo> listByCategoryAndStatus(Integer category, Integer status, Pageable pageable);
}
