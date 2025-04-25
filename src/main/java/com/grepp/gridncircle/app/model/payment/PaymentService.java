package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.order.OrderRepository;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;


    public List<OrderCheckDto> getOrderCheckInfoByEmailForPayment(String userEmail) {
        return orderRepository.selectByEmailJoinMenu(userEmail);

    }


    // 재고 부족
    @Transactional
    public void Payment(PaymentForm form) {
        List<Integer> menuIds = form.getMenuId();
        List<Integer> quantities = form.getQuantity();

        // 주문 객체 생성 및 저장
        OrderDto order = new OrderDto();
        order.setUserId(form.getUserId());
        order.setUserEmail(form.getUserEmail());
        order.setUserAddress(form.getUserAddress());
        order.setStatus(OrderStatus.ACCEPTED);

        paymentRepository.insertOrder(order);
        int orderId = order.getId();

        // 메뉴별 수량 loop 처리
        for (int i = 0; i < menuIds.size(); i++) {
            int menuId = menuIds.get(i);
            int quantity = quantities.get(i);

            MenuDto menu = paymentRepository.selectById(menuId);

            if (menu.getAmount() < quantity) {
                throw new RuntimeException("재고 부족: " + menu.getName());
            }

            // 주문된 메뉴 저장
            OrderedMenuDto orderedMenu = new OrderedMenuDto();
            orderedMenu.setOrderId(orderId);
            orderedMenu.setMenuId(menuId);
            orderedMenu.setQuantity(quantity);

            paymentRepository.insertOrderedMenu(orderedMenu);

            // 재고 차감
            int newAmount = menu.getAmount() - quantity;
            paymentRepository.updateAmount(menuId, newAmount);
        }
    }

    // 결제 완료 후 주문 정보 확인
    public OrderDto getOrderById(int orderId) {
        return paymentRepository.selectOrderById(orderId);
    }

    // // 결제 완료 후 주문 상세 내역 확인
    public List<PaymentDto> getOrderedMenus(int orderId) {
        return paymentRepository.selectOrderedMenuByOrderId(orderId);
    }

}