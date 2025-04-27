package com.grepp.gridncircle.app.controller.web.order;

import com.grepp.gridncircle.app.controller.web.order.form.OrderCheckForm;
import com.grepp.gridncircle.app.model.order.OrderService;
import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDetailDto;
import com.grepp.gridncircle.infra.error.exceptions.CommonException;
import com.grepp.gridncircle.infra.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order-check")
@RequiredArgsConstructor
@Slf4j
public class OrderCheckController {


    private final OrderService orderService;



    @GetMapping("/guest")
    public String showGuestOrders(OrderCheckForm form, Model model) {
//        model.addAttribute("guestOrders", orderService.getGuestOrders());
        return "order-check/guest";
    }

    @PostMapping("/guest")
    public String searchGuestOrders(OrderCheckForm form, Model model) {
        String email = form.getEmail();
        List<OrderCheckDto> dto = orderService.selectByEmailJoinMenu(email);

        if (dto == null || dto.isEmpty()) {
            throw new CommonException(ResponseCode.BAD_REQUEST);
        }


        model.addAttribute("orders", dto);
        log.info(dto.toString());

        return "order-check/guest-email";
    }

    @GetMapping("/guest/{orderId}")
    public String showGuestOrderDetail(@PathVariable int orderId, Model model) {
        // 주문 ID로 비회원 주문 상세 정보 조회
        List<OrderDetailDto> guestOrderDetails = orderService.selectOrderDetailById(orderId);

        // 첫 번째 항목에서 주문 정보 추출 (주문 헤더 정보)
        if (!guestOrderDetails.isEmpty()) {
            OrderDetailDto firstItem = guestOrderDetails.get(0);
            model.addAttribute("orderHeader", firstItem); // 주문 헤더 정보용
        }

        model.addAttribute("orderDetails", guestOrderDetails); // 주문 상세 목록용
        model.addAttribute("orderId", orderId); // orderId 직접 전달

        return "order-check/guest-detail";
    }

    @GetMapping("/member")
    public String showMemberOrders(Authentication auth, Model model) {

        // 인증 안된 사람은 로그인 페이지로
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }

        String userId = auth.getName();

        // 주문 목록 가져오기 (서비스에서 userId로 조회)
        List<OrderCheckDto> memberDto = orderService.selectByUserIdJoinMenu(userId);

        model.addAttribute("orders", memberDto);

        return "order-check/member";
    }

    @GetMapping("/member/{orderId}")
    public String showMemberOrderDetail(@PathVariable int orderId, Model model) {
        // 주문 ID로 회원 주문 상세 정보 조회
        List<OrderDetailDto> memberOrderDetails = orderService.selectOrderDetailById(orderId);


        if (!memberOrderDetails.isEmpty()) {
            OrderDetailDto firstItem = memberOrderDetails.get(0);
            model.addAttribute("orderHeader", firstItem);
        }

        model.addAttribute("orderDetails", memberOrderDetails);
        model.addAttribute("orderId", orderId);

        return "order-check/member-detail";
    }


}

