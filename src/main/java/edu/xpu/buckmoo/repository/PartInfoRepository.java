package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.PartInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 兼职信息JPA接口
 */
public interface PartInfoRepository extends JpaRepository<PartInfo, String> {
    /**
     * @param category 分类
     * @param pageable 分页
     */
    Page<PartInfo> findAllByPartCategory(Integer category, Pageable pageable);

    /**
     *
     * @param category 分类
     * @param status 兼职信息状态
     * @param pageable 分页
     */
    Page<PartInfo> findAllByPartCategoryAndPartStatus(Integer category, Integer status, Pageable pageable);

    /**
     * @param partCreator 兼职发布者
     * @param pageable 分页
     */
    Page<PartInfo> findAllByPartCreator(String partCreator, Pageable pageable);

    /**
     * @param partCreator 兼职发布者
     * @param partStatus 兼职状态
     * @param pageable 分页
     */
    Page<PartInfo> findAllByPartCreatorAndPartStatus(String partCreator, Integer partStatus,Pageable pageable);
}
