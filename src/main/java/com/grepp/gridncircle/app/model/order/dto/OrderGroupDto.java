package com.grepp.gridncircle.app.model.order.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderGroupDto {
    private int orderId;
    private String orderUserEmail;
    private String orderUserName;
    private List<OrderItemDto> items;
}
