package com.grepp.gridncircle.app.model.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailDto {

    // 테이블 위 정보 (주문id, 주문 가격 총 합계, 주문 상태, 주소)
    private int orderId;
    private int totalPrice;
    private String orderStatus;
    private String userAddress;

    // 테이블 안 (상품 이름, 하나 가격, 개수, 가격 총합)
    private String menuName;
    private int menuPrice;
    private int quantity;
    private int totalPricePerItem;

    private List<OrderedMenuDto> orderedMenuList; // 주문한 상품 리스트
}