package com.grepp.gridncircle.app.model.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {

    private int id;
    private String name;
    private int quantity;
    private int price;

}
