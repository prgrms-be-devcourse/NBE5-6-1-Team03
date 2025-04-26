package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.order.OrderRepository;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    // 재고 부족 여부 확인
    public List<String> checkStock(List<PaymentDto> orderedMenus) {
        List<String> errorMessages = new ArrayList<>();

        for (PaymentDto menu : orderedMenus) {
            MenuDto menuItem = paymentRepository.selectById(menu.getId());
            if (menuItem.getAmount() < menu.getQuantity()) {
                errorMessages.add("재고 부족: " + menuItem.getName());
            }
        }

        return errorMessages;
    }

    @Transactional
    public int Payment(PaymentForm form) {
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

        // 주문된 메뉴 정보 생성
        List<PaymentDto> orderedMenus = new ArrayList<>();
        for (int i = 0; i < menuIds.size(); i++) {
            PaymentDto menuDto = new PaymentDto();
            menuDto.setId(menuIds.get(i));
            menuDto.setQuantity(quantities.get(i));
            orderedMenus.add(menuDto);
        }

        // 재고 부족 체크
        List<String> errorMessages = checkStock(orderedMenus);

        // 재고 부족 메시지가 있을 경우 예외 처리
        if (!errorMessages.isEmpty()) {
            throw new RuntimeException(String.join(", ", errorMessages));
        }

        // 메뉴별 수량 loop 처리(재고 부족하면 롤백)
        for (int i = 0; i < menuIds.size(); i++) {
            int menuId = menuIds.get(i);
            int quantity = quantities.get(i);

            MenuDto menu = paymentRepository.selectById(menuId);

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

        return orderId;
    }

    // 결제 완료 후 주문 정보 확인
    public OrderDto getOrderById(int orderId) {
        return paymentRepository.selectOrderById(orderId);
    }

    // 결제 완료 후 주문 상세 내역 확인
    public List<PaymentDto> getOrderedMenus(int orderId) {
        return paymentRepository.selectOrderedMenuByOrderId(orderId);
    }
}
