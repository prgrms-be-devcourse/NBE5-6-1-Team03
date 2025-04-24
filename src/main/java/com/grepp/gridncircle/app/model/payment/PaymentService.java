package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // 주문 상태 변경
    public void changeStatus(int orderId, String status) {
        paymentRepository.updateOrderStatus(orderId, status);
    }

    public void placeOrder(OrderDto order, List<OrderedMenuDto> menuItems) {
        // 주문 등록 → order.id 생성됨
        paymentRepository.insertOrder(order);

        // 각 메뉴에 orderId 설정 후 DB에 insert
        for (OrderedMenuDto menu : menuItems) {
            menu.setOrderId(order.getId());
            paymentRepository.insertOrderedMenu(menu);
        }
    }
}

