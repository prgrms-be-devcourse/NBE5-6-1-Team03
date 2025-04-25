package com.grepp.gridncircle.app.controller.web.payment.form;

import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.List;
import lombok.Data;

@Data
public class PaymentForm {

    private String userId;
    private String userEmail;
    private String userAddress;
    private List<Integer> menuId;
    private List<Integer> quantity;


}

