package com.grepp.gridncircle.app.model.order.dto;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

// 결제 완료 후 주문 1건 저장
@Data
public class OrderDto {
    private int id;
    private String userId;
    private String name;
    private String userAddress;
    private String userEmail;
    private LocalDateTime createdAt = LocalDateTime.now();
    private OrderStatus status;


    private List<OrderedMenuDto> orderedMenus;
}
