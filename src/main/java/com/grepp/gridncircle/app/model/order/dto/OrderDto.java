package com.grepp.gridncircle.app.model.order.dto;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

}
