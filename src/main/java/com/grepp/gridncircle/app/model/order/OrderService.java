package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderInfoDto;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderCheckDto> selectByEmailJoinMenu(String email) {
        return orderRepository.selectByEmailJoinMenu(email);
    }

    // 회원 : userId 로 주문 조회
    public List<OrderCheckDto> selectByUserIdJoinMenu(String userId) {
        return orderRepository.selectByUserIdJoinMenu(userId);
    }
    private final OrderRepository orderRepository;

    public List<OrderInfoDto> getTodayOrderList() {
        List<OrderInfoDto> orderInfoList = getOrderInfoByDate(LocalDate.now());
        orderInfoList.forEach(e -> {
            OrderStatus status = OrderStatus.valueOf(e.getStatus());
            e.setStatus(status.getDesc());
        });
        return orderInfoList;
    }

    public List<OrderInfoDto> getDDayOrderList(LocalDate date) {
        List<OrderInfoDto> orderInfoList = getOrderInfoByDate(date);
        orderInfoList.forEach(e -> {
            OrderStatus status = OrderStatus.valueOf(e.getStatus());
            e.setStatus(status.getDesc());
        });
        return orderInfoList;
    }

    private List<OrderInfoDto> getOrderInfoByDate(LocalDate date) {
        LocalDateTime dateTime = date.atStartOfDay();
        LocalDateTime startDateTime = dateTime.minusDays(1).plusHours(14);
        LocalDateTime endDateTime = dateTime.plusHours(14);
        return orderRepository.selectOrderInfoByDate(startDateTime,
            endDateTime);
    }

    @Transactional
    public void updateStatus(int id, String desc) {
        OrderStatus status = OrderStatus.getStatus(desc);
        OrderDto orderDto = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
        orderRepository.updateStatus(id, status);
    }
}
