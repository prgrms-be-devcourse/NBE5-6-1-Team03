package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import java.util.List;

import com.grepp.gridncircle.app.model.order.dto.OrderDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderCheckDto> selectByEmailJoinMenu(String email) {
        return orderRepository.selectByEmailJoinMenu(email);
    }

    // 회원 : userId 로 주문 조회
    public List<OrderCheckDto> selectByUserIdJoinMenu(String userId) {
        return orderRepository.selectByUserIdJoinMenu(userId);
    }

    // 비회원 주문 1건 상세보기 조회
    public List<OrderDetailDto> selectGuestOrderDetailById(int orderId) {
        return orderRepository.selectGuestOrderDetailById(orderId);
    }
}
