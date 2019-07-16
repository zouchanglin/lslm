package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.repository.PartCategoryRepository;
import edu.xpu.buckmoo.service.PartCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PartCategoryServiceImpl implements PartCategoryService {
    @Autowired
    private PartCategoryRepository partCategoryRepository;

    @Override
    public List<PartCategory> getAll() {
        return partCategoryRepository.findAll();
    }
}
