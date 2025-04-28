package com.grepp.gridncircle.app.model.order.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSalesDto {
    private LocalDate orderDate;
    private int totalAmount;
}
