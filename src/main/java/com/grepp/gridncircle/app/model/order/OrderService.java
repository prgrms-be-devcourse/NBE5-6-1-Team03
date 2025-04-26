package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderGroupDto;
import com.grepp.gridncircle.app.model.order.dto.OrderItemDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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

    public List<OrderGroupDto> getTodayOrderList() {
        List<OrderGroupDto> orderGroupList = getOrderInfoByDate(LocalDate.now());
        for (OrderGroupDto orderGroupDto : orderGroupList) {
            List<OrderItemDto> items = orderGroupDto.getItems();
            items.forEach(e -> {
                OrderStatus status = OrderStatus.valueOf(e.getStatus());
                e.setStatus(status.getDesc());
            });
        }
        log.info("{}", orderGroupList);
        return orderGroupList;
    }

    public List<OrderGroupDto> getDDayOrderList(LocalDate date) {
        List<OrderGroupDto> orderGroupList = getOrderInfoByDate(date);
        for (OrderGroupDto orderGroupDto : orderGroupList) {
            List<OrderItemDto> items = orderGroupDto.getItems();
            items.forEach(e -> {
                OrderStatus status = OrderStatus.valueOf(e.getStatus());
                e.setStatus(status.getDesc());
            });
        }
        log.info("{}", orderGroupList);
        return orderGroupList;
    }

    private List<OrderGroupDto> getOrderInfoByDate(LocalDate date) {
        LocalDateTime dateTime = date.atStartOfDay();
        LocalDateTime startDateTime = dateTime.minusDays(1).plusHours(14);
        LocalDateTime endDateTime = dateTime.plusHours(14);
        return orderRepository.selectGroupedOrders(startDateTime, endDateTime);
    }

    @Transactional
    public void updateOrderGroupStatus(String orderUserEmail, LocalDateTime orderDateTime, String desc) {
        OrderStatus status = OrderStatus.getStatus(desc);
        LocalDateTime condition = orderDateTime.toLocalDate().atStartOfDay().plusHours(14);
        if (orderDateTime.isBefore(condition)) { // 주문일시가 당일 14시 이전일 경우
            orderRepository.updateGroupStatus(orderUserEmail, status, condition.minusDays(1),
                condition);
        } else { // 주문일시가 당일 14시 이후일 경우
            orderRepository.updateGroupStatus(orderUserEmail, status, condition,
                condition.plusDays(1));
        }
    }

    public OrderGroupDto getOrderByIdAndDateTIme(int orderId, LocalDateTime orderDateTime) {
        OrderGroupDto orderGroup = orderRepository.findByIdAndDateTime(orderId, orderDateTime);
        if (orderGroup == null) {
            throw new RuntimeException("존재하지 않는 주문입니다.");
        }
        return orderGroup;
    }

    @Transactional
    public void updateOrderStatus(int orderId, LocalDateTime orderDateTime, OrderStatus orderStatus) {
        orderRepository.updateStatusByIdAndDateTime(orderId, orderDateTime, orderStatus);
    }

    // 시간을 기준으로 주문이 속하는 기준일자 계산
    public LocalDate getBaseDate(LocalDateTime orderDateTime) {
        LocalDateTime condition = orderDateTime.toLocalDate().atStartOfDay().plusHours(14);
        if (orderDateTime.isBefore(condition)) { // 당일 14시 이전
            return orderDateTime.toLocalDate();
        }
        return orderDateTime.toLocalDate().plusDays(1);
    }

    public List<OrderGroupDto> getTimeZoneOrderList(LocalDateTime orderDateTime) {
        LocalDate baseDate = getBaseDate(orderDateTime);
        return getOrderInfoByDate(baseDate);
    }
}
