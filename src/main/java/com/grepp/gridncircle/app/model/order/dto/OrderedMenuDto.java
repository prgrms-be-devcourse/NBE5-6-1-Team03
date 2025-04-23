package com.grepp.gridncircle.app.model.order.dto;

import lombok.Data;
// 주문된 메뉴들을 각각 저장
@Data
public class OrderedMenuDto {
    private Integer orderId;
    private Integer menuId;
    private Integer quantity;

}
