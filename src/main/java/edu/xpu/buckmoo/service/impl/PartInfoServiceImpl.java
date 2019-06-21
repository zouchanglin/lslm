package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.PartInfo;
import edu.xpu.buckmoo.repository.PartInfoRepository;
import edu.xpu.buckmoo.service.PartInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tim
 * @version 1.0
 * @className PartInfoService
 * @description
 * @date 2019-06-22 00:38
 */
@Service
public class PartInfoServiceImpl implements PartInfoService {
    @Autowired
    private PartInfoRepository partRep;

    @Override
    public PartInfo findOneById(String partId) {
        return partRep.findById(partId).orElse(null);
    }
}
