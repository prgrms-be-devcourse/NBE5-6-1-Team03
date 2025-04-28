package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDetailDto;
import com.grepp.gridncircle.app.model.order.dto.OrderGroupDto;
import com.grepp.gridncircle.app.model.order.dto.OrderSalesDto;
import com.grepp.gridncircle.app.model.order.dto.OrderStatsDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // (비)회원 주문 1건 상세보기 조회
    public List<OrderDetailDto> selectOrderDetailById(int orderId) {
        return orderRepository.selectOrderDetailById(orderId);
    }

    // 오늘 주문내역 조회
    public List<OrderGroupDto> getTodayOrderList() {
        List<OrderGroupDto> orderGroupList = getOrderInfoByDate(LocalDate.now());
        log.info("{}", orderGroupList);
        return orderGroupList;
    }

    // 특정일 기준 주문내역 조회
    public List<OrderGroupDto> getDDayOrderList(LocalDate date) {
        List<OrderGroupDto> orderGroupList = getOrderInfoByDate(date);
        log.info("{}", orderGroupList);
        return orderGroupList;
    }

    // 이메일, 주문일자 기준으로 일괄 배송처리
    @Transactional
    public void updateOrderGroupStatus(String orderUserEmail, LocalDateTime orderDateTime, String status) {
        OrderStatus orderStatus = OrderStatus.getStatus(status);
        LocalDateTime condition = orderDateTime.toLocalDate().atStartOfDay().plusHours(14);
        if (orderDateTime.isBefore(condition)) { // 주문일시가 당일 14시 이전일 경우
            orderRepository.updateGroupStatus(orderUserEmail, orderStatus, condition.minusDays(1),
                condition);
        } else { // 주문일시가 당일 14시 이후일 경우
            orderRepository.updateGroupStatus(orderUserEmail, orderStatus, condition,
                condition.plusDays(1));
        }
    }

    // 주문 상세조회
    public OrderGroupDto getOrderByIdAndDateTIme(int orderId, LocalDateTime orderDateTime) {
        LocalDateTime from = orderDateTime.truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime to = from.plusSeconds(1);
        OrderGroupDto orderGroup = orderRepository.findByIdAndDateTime(orderId, from, to);
        if (orderGroup == null) {
            throw new RuntimeException("존재하지 않는 주문입니다.");
        }
        return orderGroup;
    }

    // 주문 상세조회에서 상태업데이트
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

    private List<OrderGroupDto> getOrderInfoByDate(LocalDate date) {
        LocalDateTime dateTime = date.atStartOfDay();
        LocalDateTime startDateTime = dateTime.minusDays(1).plusHours(14);
        LocalDateTime endDateTime = dateTime.plusHours(14);
        return orderRepository.selectGroupedOrders(startDateTime, endDateTime);
    }

    public List<OrderGroupDto> getTimeZoneOrderList(LocalDateTime orderDateTime) {
        LocalDate baseDate = getBaseDate(orderDateTime);
        return getOrderInfoByDate(baseDate);
    }

    public List<OrderStatsDto> getTop5Menu(LocalDate startDate, LocalDate endDate) {
        LocalDateTime from = startDate.atStartOfDay();
        LocalDateTime to = endDate.atStartOfDay().plusHours(23).plusMinutes(59).plusSeconds(59);
        return orderRepository.getTop5Menu(from, to);
    }

    public List<OrderSalesDto> getDailySales(LocalDate startDate, LocalDate endDate) {
        LocalDateTime from = startDate.atStartOfDay();
        LocalDateTime to = endDate.atStartOfDay().plusHours(23).plusMinutes(59).plusSeconds(59);
        List<OrderSalesDto> dbSales =  orderRepository.getDailySales(from, to);

        Map<LocalDate, Integer> salesMap = new HashMap<>();
        for (OrderSalesDto dto : dbSales) {
            salesMap.put(dto.getOrderDate(), dto.getTotalAmount());
        }

        // start ~ end 날짜별로 순회하면서 없는 날짜는 0으로 채움
        List<OrderSalesDto> result = new ArrayList<>();
        LocalDate currentDate = from.toLocalDate();
        LocalDate lastDate = to.toLocalDate();

        while (!currentDate.isAfter(lastDate)) {
            int amount = salesMap.getOrDefault(currentDate, 0);
            OrderSalesDto dailySales = new OrderSalesDto();
            dailySales.setOrderDate(currentDate);
            dailySales.setTotalAmount(amount);
            result.add(dailySales);

            currentDate = currentDate.plusDays(1);
        }

        return result;
    }

    public Map<String, List<?>> convertToChartData(List<OrderSalesDto> salesList) {
        List<String> labels = new ArrayList<>(); // 날짜 문자열 리스트
        List<Integer> data = new ArrayList<>();  // 매출 데이터 리스트

        for (OrderSalesDto dto : salesList) {
            labels.add(dto.getOrderDate().toString());
            data.add(dto.getTotalAmount());
        }

        Map<String, List<?>> chartData = new HashMap<>();
        chartData.put("labels", labels);
        chartData.put("data", data);

        return chartData;
    }

}
