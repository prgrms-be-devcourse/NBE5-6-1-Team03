package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.payment.dto.PaymentRequestDto;
import com.grepp.gridncircle.app.model.payment.entity.Order;
import com.grepp.gridncircle.app.model.payment.entity.OrderedMenu;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;


    public void insertOrder(PaymentRequestDto dto){
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setUserName(dto.getUserName());
        order.setUserEmail(dto.getUserEmail());
        order.setUserAddress(dto.getUserAddress());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("ORDER_ACCEPT");
    }


    // 1. 전체 주문 조회
    public List<Order> selectAll() {
        return paymentRepository.selectAll();
    }

    // 2. 결제 상태 업데이트
    public void updateStatus(int orderId, String status) {
        paymentRepository.updateOrderStatus(orderId, status);
    }

}
