package com.grepp.gridncircle.app.controller.web.order;

import com.grepp.gridncircle.app.model.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order/order-check")
public class OrderCheckController {


//    private final OrderService orderService;
//
//    @RequiredArgsConstructor
//    public OrderCheckController(OrderService orderService) {
//        this.orderService = orderService;
//    }


    @GetMapping("/guest")
    public String showGuestOrders(Model model) {
//        model.addAttribute("guestOrders", orderService.getGuestOrders());
        return "order/order-check-guest";
    }

    @GetMapping("/member")
    public String showMemberOrders(Model model) {
//        model.addAttribute("memberOrders", orderService.getMemberOrders());
        return "order/order-check-member";
    }


}

