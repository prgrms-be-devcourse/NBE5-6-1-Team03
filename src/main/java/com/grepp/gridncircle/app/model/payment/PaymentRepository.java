package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentRepository {


    // 재고 조회
    MenuDto selectById(@Param("id") int id);

    // 결제 후 재고 업데이트
    void updateAmount(@Param("id") int id, @Param("amount") int amount);

    // 주문 메뉴 저장
    void insertOrderedMenu(OrderedMenuDto orderedMenu);

    // 결제 완료 후 주문 정보 확인
    OrderDto selectOrderById(int orderId);

    // 결제 완료 후 주문 상세 내역 확인
    List<PaymentDto> selectOrderedMenuByOrderId(int orderId);

    // 회원 주문 저장
    void insertOrderForMember(OrderDto order);

    // 비회원 주문 저장
    void insertOrderForGuest(OrderDto order);
}
