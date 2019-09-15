package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.PartInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 兼职信息JPA接口
 */
public interface PartInfoRepository extends JpaRepository<PartInfo, String> {
    /**
     * 根据分类查询兼职信息
     * @param category 分类
     * @param pageable 分页
     */
    Page<PartInfo> findAllByPartCategory(Integer category, Pageable pageable);

    /**
     * 根据分类和兼职状态查询兼职信息
     * @param category 分类
     * @param status 兼职信息状态
     * @param pageable 分页
     */
    Page<PartInfo> findAllByPartCategoryAndPartStatus(Integer category, Integer status, Pageable pageable);

    /**
     * 查询我发布的兼职
     * @param partCreator 兼职发布者
     * @param pageable 分页
     */
    Page<PartInfo> findAllByPartCreator(String partCreator, Pageable pageable);

    /**
     * 查询我接手的兼职
     * @param partEmploy 兼职接受者
     * @param pageable 分页参数
     * @return 兼职信息分页
     */
    Page<PartInfo> findAllByPartEmploy(String partEmploy, Pageable pageable);

    /**
     * 根据兼职状态查询兼职信息
     * @param partStatus 兼职状态
     * @param pageable 分页参数
     * @return 兼职信息分页
     */
    Page<PartInfo> findAllByPartStatus(Integer partStatus, Pageable pageable);
}
