package edu.xpu.buckmoo.service;

import edu.xpu.buckmoo.dataobject.PartInfo;

public interface PartInfoService {
    PartInfo findOneById(String partId);
}
