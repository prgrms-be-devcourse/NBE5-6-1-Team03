package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaymentRepository {



    // 주문 조회
    OrderDto selectOrderById(@Param("orderId") int orderId);

    // 주문된 메뉴 목록 조회
    List<PaymentDto> selectOrderedMenuByOrderId(@Param("orderId") int orderId);

    // 주문 총 금액 조회
    int selectTotalPriceByOrderId(@Param("orderId") int orderId);

    // 계산된 총 금액을 orders 테이블에 반영
    void applyTotalPrice(@Param("orderId") int orderId, @Param("totalPrice") int totalPrice);

    // 주문 상태 업데이트
    void updateOrderStatus(@Param("orderId") int orderId, @Param("status") OrderStatus status);


}
