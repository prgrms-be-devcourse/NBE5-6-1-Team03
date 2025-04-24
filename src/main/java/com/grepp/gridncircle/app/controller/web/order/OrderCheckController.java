package com.grepp.gridncircle.app.controller.web.order;

import com.grepp.gridncircle.app.controller.web.order.form.OrderCheckForm;
import com.grepp.gridncircle.app.model.order.OrderService;
import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order-check")
@RequiredArgsConstructor
@Slf4j
public class OrderCheckController {


    private final OrderService orderService;
//
//    @RequiredArgsConstructor
//    public OrderCheckController(OrderService orderService) {
//        this.orderService = orderService;
//    }


    @GetMapping("/guest")
    public String showGuestOrders(OrderCheckForm form, Model model) {
//        model.addAttribute("guestOrders", orderService.getGuestOrders());
        return "order-check/guest";
    }

    @PostMapping("/guest")
    public String searchGuestOrders(OrderCheckForm form, Model model) {
        String email = form.getEmail();
        List<OrderCheckDto> dto = orderService.selectByEmailJoinMenu(email);

        model.addAttribute("orders", dto);
        log.info(dto.toString());

        return "order-check/guest-email";
    }

    @GetMapping("/member")
    public String showMemberOrders(Model model) {
//        model.addAttribute("memberOrders", orderService.getMemberOrders());
        return "order-check/member";
    }


}

