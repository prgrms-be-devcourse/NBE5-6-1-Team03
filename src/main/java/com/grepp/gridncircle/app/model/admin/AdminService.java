package com.grepp.gridncircle.app.model.admin;

import com.grepp.gridncircle.app.model.order.OrderService;
import com.grepp.gridncircle.app.model.order.dto.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OrderService orderService;

    public Order findById(int id) {
        return null;
    }
}
