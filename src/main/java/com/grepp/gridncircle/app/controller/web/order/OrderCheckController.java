package com.grepp.gridncircle.app.controller.web.order;

import com.grepp.gridncircle.app.controller.web.order.form.OrderCheckForm;
import com.grepp.gridncircle.app.model.order.OrderService;
import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import com.grepp.gridncircle.infra.error.exceptions.CommonException;
import com.grepp.gridncircle.infra.response.ResponseCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order-check")
@RequiredArgsConstructor
@Slf4j
public class OrderCheckController {


    private final OrderService orderService;
//
//    @RequiredArgsConstructor
//    public OrderCheckController(OrderService orderService) {
//        this.orderService = orderService;
//    }


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

    @GetMapping("/member")
    public String showMemberOrders(Model model) {

//        // SecurityContext에서 인증된 사용자 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // 인증 안된 사람은 로그인 페이지로
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return "redirect:/login";
//        }
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String userId = userDetails.getUsername(); // 비밀번호는 로그인에서 처리해서 비교할 필요 x
//
//        // 주문 목록 조회
//        List<Order> memberOrders = orderService.getMemberOrdersByUserId(userId);
//        model.addAttribute("memberOrders", memberOrders);

        return "order-check/member";
    }


}

