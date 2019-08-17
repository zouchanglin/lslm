package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.VO.PartInfoVO;
import edu.xpu.buckmoo.dataobject.order.PartInfo;
import org.springframework.data.domain.Page;

public interface PageToPartInfoVO {
    PartInfoVO partPageToPartInfoVO(Page<PartInfo> partInfoPage);
}
