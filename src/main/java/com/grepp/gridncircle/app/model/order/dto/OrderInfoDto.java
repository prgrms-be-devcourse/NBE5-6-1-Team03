package com.grepp.gridncircle.app.model.order.dto;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;

@Data
public class OrderInfoDto {
    private int orderId;
    private LocalDateTime orderDate;
    private String orderUserName;
    private String orderUserEmail;
    private String menuName;
    private int quantity;
    private String status;

    public String getFormattedOrderDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
