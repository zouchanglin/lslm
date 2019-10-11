package edu.xpu.buckmoo.repository.order;

import edu.xpu.buckmoo.dataobject.order.UserMemberOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMemberOrderRepository extends JpaRepository<UserMemberOrder, String> {
    UserMemberOrder findFirstByOrderCollection(String orderCollection);
}