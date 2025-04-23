package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.payment.entity.OrderedMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;

@Mapper
public interface PaymentRepository {

    void insertOrder(Order order);
    void insertOrderedMenu(OrderedMenu menu);

    List<Order> selectAll();

    void updateOrderStatus(@Param("orderId") int orderId, @Param("status") String status);

}
