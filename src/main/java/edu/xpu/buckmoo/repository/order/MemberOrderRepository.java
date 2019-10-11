package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.MemberOrder;
import edu.xpu.buckmoo.dataobject.order.UserMemberOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOrderRepository extends JpaRepository<MemberOrder, String> {
}
