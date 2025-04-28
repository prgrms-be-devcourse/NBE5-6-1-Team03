package com.grepp.gridncircle.app.model.order.dto;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import lombok.Data;

@Data
public class OrderCheckDto {

    private int id;
    private int totalQuantity;
    private int totalPrice;
    private OrderStatus orderStatus;
    private String email;

}
