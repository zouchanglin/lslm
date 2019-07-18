package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CompanyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 公司订单Dao层操作接口
 */
public interface CompanyOrderRepository extends JpaRepository<CompanyOrder, String> {
}
