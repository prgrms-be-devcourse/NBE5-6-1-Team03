package com.grepp.gridncircle.app.controller.web.payment;

import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.payment.PaymentService;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public String paymentPage(@ModelAttribute PaymentForm form, Model model) {
        model.addAttribute("paymentForm", form);
        return "payment/payment";
    }

    @PostMapping("/complete")
    public String completePayment(@RequestParam int orderId, @ModelAttribute PaymentForm form) {
        try {
            // 총금액을 orders 테이블에 반영
            paymentService.applyTotalPrice(form, orderId);

            // 결제 성공
            paymentService.updateOrderStatus(orderId, OrderStatus.PAID);

            return "redirect:/payment/success?orderId=" + orderId;
        } catch (Exception e) {
            log.error("결제 실패", e);
            return "redirect:/payment/fail";
        }
    }


    @GetMapping("/success")
    public String paymentSuccess(@RequestParam int orderId, Model model) {
        OrderDto order = paymentService.getOrderById(orderId);
        List<PaymentDto> menuItems = paymentService.getOrderedMenus(orderId);

        int totalPrice = menuItems.stream()
            .mapToInt(m -> m.getPrice() * m.getQuantity())
            .sum();

        model.addAttribute("loginUser", order);
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("totalPrice", totalPrice);

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
}