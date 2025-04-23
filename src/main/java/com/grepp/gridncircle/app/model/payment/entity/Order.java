package com.grepp.gridncircle.app.model.payment.entity;

import java.time.LocalDateTime;
import lombok.Data;

// 결제 완료 후 주문 1건 저장
@Data
public class Order {
    private Integer id;
    private String userId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private LocalDateTime createdAt;
    private String status;

}
