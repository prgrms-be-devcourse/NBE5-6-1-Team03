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


    public void placeOrder(PaymentForm form) {
        OrderDto order = new OrderDto();
        order.setUserId(form.getUserId());
        order.setUserEmail(form.getUserEmail());
        order.setUserAddress(form.getUserAddress());

        // 주문 insert → order.id 자동 생성됨
        paymentRepository.insertOrder(order);

        int totalPrice = 0;

        for (PaymentDto menu : form.getMenuItems()) {
            int menuPrice = paymentRepository.selectPriceByMenuId(menu.getId());
            totalPrice += menuPrice * menu.getQuantity();

            OrderedMenuDto orderedMenu = new OrderedMenuDto();
            orderedMenu.setOrderId(order.getId());
            orderedMenu.setMenuId(menu.getId());
            orderedMenu.setQuantity(menu.getQuantity());

            paymentRepository.insertOrderedMenu(orderedMenu);
        }

        paymentRepository.updateOrderTotalPrice(order);
    }
}

