package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.CompanyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 公司订单Dao层操作接口
 */
public interface CompanyOrderRepository extends JpaRepository<CompanyOrder, String> {
}
