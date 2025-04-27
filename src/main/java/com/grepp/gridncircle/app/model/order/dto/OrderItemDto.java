package com.grepp.gridncircle.app.model.order.dto;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class OrderItemDto {
    private int orderId;
    private String menuName;
    private int quantity;
    private int price;
    private String status;
    private LocalDateTime orderDateTime;

    public String getFormattedOrderDate() {
        return orderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // enum 영문 => 한글 desc
    public String getStatusDesc() {
        OrderStatus orderStatus = OrderStatus.getStatus(status);
        return orderStatus.getDesc();
    }
}
