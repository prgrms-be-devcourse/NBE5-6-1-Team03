package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // 결제 성공 시 주문 상태 업데이트
    public void updateOrderStatus(int orderId, OrderStatus status) {
        paymentRepository.updateOrderStatus(orderId, status);
    }

    public void applyTotalPrice(PaymentForm form, int orderId) {
        // 총 금액 계산
        int totalPrice = paymentRepository.selectTotalPriceByOrderId(orderId);

        // orders 테이블에 총 금액 반영
        paymentRepository.applyTotalPrice(orderId, totalPrice);
    }


    // 주문자 정보
    public OrderDto getOrderById(int orderId) {
        return paymentRepository.selectOrderById(orderId);
    }

    // 주문한 메뉴 요약
    public List<PaymentDto> getOrderedMenus(int orderId) {
        return paymentRepository.selectOrderedMenuByOrderId(orderId);
    }
}