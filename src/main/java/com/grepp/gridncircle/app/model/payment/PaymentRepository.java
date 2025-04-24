package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentRepository {

    // 총 금액을 계산해서 조회하는 읽기 전용
    int selectTotalPriceByOrderId(@Param("orderId") int orderId);

    void insertOrder(OrderDto orderDto);

    void insertOrderedMenu(OrderedMenuDto orderedMenuDto);

    int selectPriceByMenuId(@Param("menuId") int menuId);

    // DB에 반영(업데이트) - 총 금액 갱신
    void updateOrderTotalPrice(OrderDto order);


}
