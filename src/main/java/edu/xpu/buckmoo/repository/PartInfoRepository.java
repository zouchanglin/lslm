package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.PartInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 兼职信息JPA接口
 */
public interface PartInfoRepository extends JpaRepository<PartInfo, String> {
}
