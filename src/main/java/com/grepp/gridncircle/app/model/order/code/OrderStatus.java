package com.grepp.gridncircle.app.model.order.code;

import lombok.Data;
import lombok.Getter;


public enum OrderStatus {
    ACCEPTED("주문접수"),
    REJECTED("주문반려"),
    PAID("결제완료"),
    RELEASE("발송완료"),
    ON_THE_WAY("배송중"),
    DELIVERED("배송완료"),
    RETURN("반품"),
    EXCHANGE("교환처리"),
    ERROR("확인필요");

    private String desc;
    OrderStatus(String desc) {
        this.desc = desc;
    }

    public static OrderStatus getStatus(String desc) {
        OrderStatus[] values = OrderStatus.values();
        for (OrderStatus value : values) {
            if (value.desc.equals(desc)) {
                return value;
            }
        }
        return OrderStatus.ERROR;
    }

    public String getDesc() {
        return desc;
    }
}
