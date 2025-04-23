package com.grepp.gridncircle.app.model.payment.entity;

import lombok.Data;
// 주문된 메뉴들을 각각 저장
@Data
public class OrderedMenu {
    private Integer orderId;
    private Integer menuId;
    private Integer quantity;

}
