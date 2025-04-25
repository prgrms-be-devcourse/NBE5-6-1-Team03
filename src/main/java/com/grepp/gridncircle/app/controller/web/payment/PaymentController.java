package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.payment.PaymentService;
import com.grepp.gridncircle.infra.error.exceptions.CommonException;
import com.grepp.gridncircle.infra.response.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public String completePayment(@ModelAttribute PaymentForm form, HttpServletRequest request) {
        try {
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.");
            }
            paymentService.completePayment(form);
            return "redirect:/payment/success";  // 결제 성공 리다이렉트
        } catch (Exception e) {
            log.error("결제 실패: {}", e.getMessage());
            return "redirect:/payment/fail";  // 결제 실패 리다이렉트
        }
    }

    // GET 방식으로 /payment/success에 접근할 때 예외 처리
    @GetMapping("/success")
    public String paymentSuccess(HttpServletRequest request) {
        // GET 요청 차단
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.");
        }
        return "payment/success"; // 결제 성공 페이지
    }

    @GetMapping("/fail")
    public String paymentFail(HttpServletRequest request) {
        // GET 요청 차단
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.");
        }
        return "payment/fail"; // 결제 실패 페이지
    }

    // /payment/order-check 페이지
    @GetMapping("/order-check")
    public String orderCheckPage() {
        return "order/order-check";
    }
}
