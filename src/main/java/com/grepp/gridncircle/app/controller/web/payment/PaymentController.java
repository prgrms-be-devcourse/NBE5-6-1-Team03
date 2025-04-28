package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.order.OrderService;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.payment.PaymentService;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import com.grepp.gridncircle.infra.error.exceptions.CommonException;
import com.grepp.gridncircle.infra.response.ResponseCode;
import jakarta.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    // 결제 처리
    @PostMapping
    public String payment(@ModelAttribute PaymentForm form, HttpSession session, Model model) {

        try {
            int orderId = paymentService.Payment(form);

            // 주문 ID를 세션에 저장
            session.setAttribute("orderId", orderId);

            return "forward:/payment/success";

        } catch (RuntimeException e) {
            log.error("결제 실패: {}", e.getMessage());
            model.addAttribute("errorMessages", e.getMessage());
            return "payment/fail";
        }
    }


    @PostMapping("/success")
    public String paymentSuccess(HttpSession session, Model model) {
        Integer orderId = (Integer) session.getAttribute("orderId");

        if (orderId != null) {
            OrderDto order = paymentService.getOrderById(orderId);
            List<PaymentDto> menuItems = paymentService.getOrderedMenus(orderId);

            // 총 결제 금액 계산
            int totalPrice = menuItems.stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a h시 mm분");
            String formattedDate = order.getCreatedAt().format(formatter);

            model.addAttribute("order", order);
            model.addAttribute("menuItems", menuItems);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("orderId", orderId);
            model.addAttribute("createdAt", formattedDate);
        }

        return "payment/success";
    }


    @PostMapping("/fail")
    public String paymentFail() {
        return "payment/fail";
    }
}
