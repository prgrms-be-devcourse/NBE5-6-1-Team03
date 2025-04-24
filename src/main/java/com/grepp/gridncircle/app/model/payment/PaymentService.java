package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
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

    public void placeOrder(PaymentForm form) {
        OrderDto order = new OrderDto();
        order.setUserId(form.getUserId());
        order.setUserEmail(form.getUserEmail());
        order.setUserAddress(form.getUserAddress());
        order.setTotalPrice(form.getTotalPrice());

        paymentRepository.insertOrder(order);

        for (PaymentDto menu : form.getMenuItems()) {
            OrderedMenuDto orderedMenu = new OrderedMenuDto();
            orderedMenu.setOrderId(order.getId());
            orderedMenu.setMenuId(menu.getId());
            orderedMenu.setQuantity(menu.getQuantity());

            paymentRepository.insertOrderedMenu(orderedMenu);
        }


    }
}

