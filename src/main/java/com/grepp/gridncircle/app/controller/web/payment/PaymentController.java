package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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

    // 결제 처리
    @PostMapping("/payment")
    public String payment(@ModelAttribute PaymentForm form) {
        try {
            paymentService.Payment(form);
            return "forward:/payment/success";
        } catch (Exception e) {
            log.error("결제 실패: {}", e.getMessage());
            return "forward:/payment/fail";
        }
    }

    @PostMapping("/success")
    public String paymentSuccess() {
        return "payment/success";
    }

    @PostMapping("/fail")
    public String paymentFail() {
        return "payment/fail";
    }


}
