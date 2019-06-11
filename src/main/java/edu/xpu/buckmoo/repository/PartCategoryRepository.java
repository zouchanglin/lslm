package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.PartCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 兼职分类信息JPA接口
 */
public interface PartCategoryRepository extends JpaRepository<PartCategory, Integer> {
}
