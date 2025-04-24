package com.grepp.gridncircle.app.controller.web.order;

import com.grepp.gridncircle.app.controller.web.order.form.OrderForm;
import com.grepp.gridncircle.app.model.member.MemberService;
import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final MenuService menuService;
    private final MemberService memberService;

    @GetMapping
    public String showOrderPage(OrderForm form, Model model) {

//        String userId = authentication.getName();
        List<MenuDTO> menus = menuService.getMenuList();
//        Optional<MemberDto> member = memberService.selectById(userId);

        log.info("Menu List: {}", menus);
        model.addAttribute("menus", menus);
        
        return "order/order";
    }

    @PostMapping
    public String createOrder(@Valid OrderForm form, BindingResult bindingResult, Model model) {

        List<MenuDTO> menus = menuService.getMenuList();
        List<PaymentDto> orderedMenus = form.getMenuList();
        model.addAttribute("menus", menus);
        model.addAttribute("orderedMenus", orderedMenus);
        if (bindingResult.hasErrors()) {
            return "order/order";
        }

        OrderDto dto = form.toDto();

        return "redirect:/";
    }

}
