package com.grepp.gridncircle.app.model.order.dto;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Order {
    @NotNull
    private int id;
    private String userId;
    private String name;
    @NotNull
    private String userAddress;
    @Email
    private String userEmail;
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    private OrderStatus status;
}
