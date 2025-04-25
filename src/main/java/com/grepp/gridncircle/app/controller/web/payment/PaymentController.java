package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.payment.PaymentService;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

//    @PostMapping
//    public String paymentPage(@ModelAttribute PaymentForm form, Model model) {
//
//        log.info("Payment form : {}", form);
//
//        return "payment/payment";
//    }

    @PostMapping("/complete")
    public String completePayment(@ModelAttribute PaymentForm form) {
        try {
            paymentService.completePayment(form);
            return "redirect:/payment/success";
        } catch (Exception e) {
            log.error("결제 실패: {}", e.getMessage());
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

    @GetMapping("/order-check")

    public String orderCheckPage() {
        return "order/order-check";
    }

    // GET 방식 접근 시 400 에러
    @GetMapping("/complete")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void preventDirectAccess() {
    }
}