package edu.xpu.buckmoo.repository;

import edu.xpu.buckmoo.dataobject.CompanyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyOrderRepository extends JpaRepository<CompanyOrder, String> {
}
