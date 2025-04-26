package com.grepp.gridncircle.app.model.order.code;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ACCEPTED("주문접수"),
    REJECTED("주문거절"),
    PAID("결제완료"),
    RELEASE("상품발송"),
    ON_THE_WAY("배송중"),
    DELIVERED("배송완료"),
    RETURN("반품"),
    EXCHANGE("교환처리"),
    ERROR("확인필요");

    private final String desc;
    OrderStatus(String desc) {
        this.desc = desc;
    }

    public static OrderStatus getStatus(String desc) {
        OrderStatus[] values = OrderStatus.values();
        for (OrderStatus value : values) {
            if (value.name().equals(desc)) {
                return value;
            }
        }
        return OrderStatus.ERROR;
    }

    public String getDesc() {
        return desc;
    }
}
