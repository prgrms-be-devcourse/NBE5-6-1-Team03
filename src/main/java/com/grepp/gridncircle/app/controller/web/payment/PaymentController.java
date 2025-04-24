package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderedMenuDto;
import com.grepp.gridncircle.app.model.payment.PaymentService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment/complete")
    public String completePayment(@ModelAttribute PaymentDto dto) {
        paymentService.placeOrder(dto.toOrderDto(), dto.getMenuItems());
        return "redirect:/payment/success";
    }



    @GetMapping("/success")
    public String paymentSuccess() {
        return "payment/success";
    }

    @GetMapping("/fail")
    public String paymentFail() {
        return "payment/fail";
    }

    @GetMapping("/payment")
    public String paymentPage(Model model) {
        List<OrderedMenuDto> menuItems = ;
        model.addAttribute("menuItems", menuItems);

        return "payment/payment";
    }

    @GetMapping("/order-check")
    public String orderCheckPage() {
        return "order/order-check";
    }


}