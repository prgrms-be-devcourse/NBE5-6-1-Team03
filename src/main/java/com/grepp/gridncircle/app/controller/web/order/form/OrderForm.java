package com.grepp.gridncircle.app.controller.web.order.form;

import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class OrderForm {

    @NotNull
    private List<PaymentDto> menuList;

    private String userId;

    @NotBlank
    private String userAddress;
    @NotBlank
    @Email
    private String userEmail;

    public OrderDto toDto() {
        OrderDto dto = new OrderDto();
        dto.setUserAddress(userAddress);
        dto.setUserEmail(userEmail);
        return dto;
    }
}
