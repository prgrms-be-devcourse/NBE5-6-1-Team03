package com.grepp.gridncircle.app.model.payment.dto;

import java.util.List;
import lombok.Data;
// 주문자 + 메뉴 정보 묶어서 전달(사용자 요청용)
@Data
public class PaymentRequest {
    private String userId;
    private String userName;
    private String userEmail;
    private String userAddress;

    private List<OrderedMenu> menuItems;


}
