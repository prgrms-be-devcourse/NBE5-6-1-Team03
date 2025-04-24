package com.grepp.gridncircle.app.controller.web.payment.form;

import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;

@Data
public class PaymentForm {
    private String userId;

    @NotBlank(message = "이름을 적어주세요")
    private String userName;

    @NotBlank(message = "이메일을 적어주세요")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String userEmail;

    @NotBlank(message = "주소를 적어주세요")
    private String userAddress;

    @NotEmpty(message = "주문한 메뉴가 비어있습니다.")
    private List<OrderedMenuDto> menuItems;
}

