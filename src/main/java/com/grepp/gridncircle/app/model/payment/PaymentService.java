package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.order.OrderRepository;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public void processOrder(OrderDto orderDto, List<OrderedMenuDto> menuList) {
        // orders 테이블에 주문 정보 저장
        paymentRepository.insertOrder(orderDto);

        // ordered_menu 테이블에 메뉴 리스트 저장
        for (OrderedMenuDto item : menuList) {
            item.setOrderId(orderDto.getId());
            paymentRepository.insertOrderedMenu(item);
        }

        log.info("결제 완료: orderId={}, 총 {}개 메뉴", orderDto.getId(), menuList.size());
    }
}
