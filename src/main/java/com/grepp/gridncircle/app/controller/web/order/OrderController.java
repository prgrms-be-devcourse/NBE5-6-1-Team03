package com.grepp.gridncircle.app.controller.web.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {


//    @GetMapping("/order/check/member")
//    public String memberOrderCheck(Model model, @SessionAttribute("member") Member member) {
//        List<Order> orders = orderService.findByMemberId(member.getId());
//        model.addAttribute("orders", orders);
//        return "order/member/order-check";
//    }

    @GetMapping("/order/check/guest")
    public String guestOrderCheckForm() {
        return "order/guest/order-check";
    }

}
