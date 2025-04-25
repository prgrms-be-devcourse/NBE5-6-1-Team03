package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.payment.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


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

    // POST 방식 결제 처리
    @PostMapping("/complete")
    public String completePayment(@ModelAttribute PaymentForm form) {
        try {
            paymentService.completePayment(form); // 결제 처리
            return "redirect:/payment/success";
        } catch (Exception e) {
            log.error("결제 실패: {}", e.getMessage());
            return "redirect:/payment/fail";
        }
    }

    @GetMapping("/success")
    public String paymentSuccess(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.");
        }
        return "payment/success";
    }

    @GetMapping("/fail")
    public String paymentFail(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.");
        }
        return "payment/fail";
    }

    @GetMapping("/order-check")
    public String orderCheckPage() {
        return "order/order-check";

    }

}
