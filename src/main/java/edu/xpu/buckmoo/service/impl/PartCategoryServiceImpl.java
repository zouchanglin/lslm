package edu.xpu.buckmoo.service.impl;

import edu.xpu.buckmoo.dataobject.PartCategory;
import edu.xpu.buckmoo.repository.PartCategoryRepository;
import edu.xpu.buckmoo.service.PartCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PartCategoryServiceImpl implements PartCategoryService {
    private final PartCategoryRepository partCategoryRepository;

    public PartCategoryServiceImpl(PartCategoryRepository partCategoryRepository) {
        this.partCategoryRepository = partCategoryRepository;
    }

    @Override
    public List<PartCategory> getAll() {
        return partCategoryRepository.findAll();
    }

    @Override
    public PartCategory findById(Integer partCategory) {
        return partCategoryRepository.findById(partCategory).orElse(null);
    }
}
