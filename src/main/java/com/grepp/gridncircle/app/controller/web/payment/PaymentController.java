package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.model.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/complete")
    public String completePayment(
        @RequestParam String email,
        Model model) {
        try {
            // 결제 로직 처리
            return "redirect:/payment/success";
        } catch (Exception e) {
            log.error("결제 실패", e);
            return "redirect:/payment/fail";
        }
    }

    @GetMapping("/success")
    public String paymentSuccess() {
        return "payment/success";
    }

    @GetMapping("/fail")
    public String paymentFail() {
        return "payment/fail";
    }

    @GetMapping("/order/detail")
    public String orderDetailPage() {
        return "order/order-detail";
    }
}
