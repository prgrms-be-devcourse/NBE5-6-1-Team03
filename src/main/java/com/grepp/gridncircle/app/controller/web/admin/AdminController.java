package com.grepp.gridncircle.app.controller.web.admin;

import com.grepp.gridncircle.app.controller.web.menu.form.MenuRegistForm;
import com.grepp.gridncircle.app.model.admin.AdminService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MenuService menuService;

    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }

    // 주문 관리
    @GetMapping("orders")
    public String orders() {
        return "admin/order/order";
    }

    @GetMapping("orders/{id}")
    public String orderDetail(@PathVariable int id, Model model) {
//        Order order = orderService.findById(id);
//        model.addAttribute("order", order);
        return "admin/order/order-detail";
    }

    // 상품 관리
    @GetMapping("menu")
    public String menu() {
        return "admin/menu/menu";
    }

    @GetMapping("menu/{id}")
    public String menuDetail(@PathVariable int id, Model model) {
//        Product product = productService.findById(id);
//        model.addAttribute("menu", menu);
        return "admin/menu/menu-detail";
    }

    @GetMapping("menu/new")
    public String menuAdd(MenuRegistForm form) {
        return "admin/menu/menu-new";
    }

    @PostMapping("menu/new")
    public String menuRegist(
        @Valid MenuRegistForm form,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/menu/menu-new";
        }
        menuService.registMenu(form.getThumbnail(), form.toDto());
        return "redirect:/admin/menu";
    }
}
