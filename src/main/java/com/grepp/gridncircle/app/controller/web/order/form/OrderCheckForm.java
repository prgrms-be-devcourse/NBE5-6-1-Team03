package com.grepp.gridncircle.app.controller.web.order.form;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class OrderCheckForm {

    @Email
    private String email;
}
