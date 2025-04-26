package com.grepp.gridncircle.app.model.order.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderGroupDto {
    private String orderUserEmail;
    private String orderUserName;
    private String orderUserAddress;
    private List<OrderItemDto> items;

    public String getTotalPrice() {
        int totalPrice = 0;
        for (OrderItemDto item : items) {
            totalPrice += (item.getPrice() * item.getQuantity());
        }
        return String.valueOf(totalPrice);
    }
}
