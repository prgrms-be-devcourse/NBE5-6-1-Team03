package com.grepp.gridncircle.app.model.order.dto;

import lombok.Data;

@Data
public class OrderedMenu {
    private int orderId;
    private int menuId;
    private int quantity = 1;
}
