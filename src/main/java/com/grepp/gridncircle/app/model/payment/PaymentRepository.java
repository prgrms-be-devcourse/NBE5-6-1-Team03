package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentRepository {

    int selectTotalPriceByOrderId(@Param("orderId") int orderId);


    void insertOrder(OrderDto orderDto);

    void insertOrderedMenu(OrderedMenuDto orderedMenuDto);

    void updateOrderStatus(@Param("orderId") int orderId, @Param("status") String status);

}
