package com.grepp.gridncircle.app.controller.web.order;

import com.grepp.gridncircle.app.controller.web.order.form.OrderForm;
import com.grepp.gridncircle.app.controller.web.payment.form.PaymentForm;
import com.grepp.gridncircle.app.model.member.MemberService;
import com.grepp.gridncircle.app.model.member.dto.MemberDto;
import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import com.grepp.gridncircle.app.model.payment.dto.PaymentDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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
    private final ImageService imageService;

    @GetMapping
    public String showOrderPage(Authentication authentication, OrderForm form, Model model) {

        Optional<Authentication> auth = Optional.ofNullable(authentication);

        List<MenuImageDto> imageList = imageService.getAllImage();
        Map<Integer, List<MenuImageDto>> imageMap = imageList.stream()
                .collect(Collectors.groupingBy(MenuImageDto::getMenuId));

        String id = null;

        if (auth.isPresent()) {
            id = auth.get().getName();
        }

        if(id != null) {
            Optional<MemberDto> memberDto = memberService.selectById(id);
            MemberDto member = memberDto.get();
            model.addAttribute("member", member);
        }
        List<MenuDto> menus = menuService.getMenuList();

        model.addAttribute("menus", menus);
        model.addAttribute("imageMap", imageMap);
        
        return "order/order";
    }

    @PostMapping
    public String createOrder(@Valid OrderForm form, BindingResult bindingResult, Model model) {

        List<MenuDto> menus = menuService.getMenuList();
        List<PaymentDto> orderedMenus = form.getMenuList();

        List<MenuImageDto> imageList = imageService.getAllImage();
        Map<Integer, List<MenuImageDto>> imageMap = imageList.stream()
                .collect(Collectors.groupingBy(MenuImageDto::getMenuId));

        model.addAttribute("imageMap", imageMap);
        model.addAttribute("menus", menus);
        model.addAttribute("orderedMenus", orderedMenus);
        if (bindingResult.hasErrors()) {
            return "order/order";
        }

        int totalPrice = 0;

        for (PaymentDto payment : orderedMenus) {
            totalPrice += payment.getPrice() * payment.getQuantity();
        }

        if (form.getUserId().isEmpty()) {
            form.setUserId(null);
        }

        log.info("form :{}", form);

        model.addAttribute("paymentForm", new PaymentForm());

        model.addAttribute("orderDetails", form);
        model.addAttribute("totalPrice", totalPrice);

        return "order/order-detail";
    }

}
