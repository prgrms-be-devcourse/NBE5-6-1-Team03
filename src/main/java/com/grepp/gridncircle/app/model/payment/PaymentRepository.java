package com.grepp.gridncircle.app.model.payment;

import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {

    void insertOrder(OrderDto order);  // <insert useGeneratedKeys="true" keyProperty="id" ...>

    void insertOrderedMenu(OrderedMenuDto menu);  // <insert ...>
}
