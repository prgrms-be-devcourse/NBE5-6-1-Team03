package com.grepp.gridncircle.app.model.order.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class OrderItemDto {
    private String menuName;
    private int quantity;
    private String status;
    private LocalDateTime orderDate;

    public String getFormattedOrderDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
