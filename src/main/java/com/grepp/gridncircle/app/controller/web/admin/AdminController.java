package com.grepp.gridncircle.app.controller.web.admin;

import com.grepp.gridncircle.app.controller.web.menu.form.MenuRegistForm;
import com.grepp.gridncircle.app.model.admin.AdminService;
import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final MenuService menuService;
    private final ImageService imageService;

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
    public String menu(Model model) {
        List<MenuDTO> menuList = menuService.getMenuList();
        List<MenuImageDTO> imageList = imageService.getAllImage();
        Map<Integer, List<MenuImageDTO>> imageMap = imageList.stream()
            .collect(Collectors.groupingBy(MenuImageDTO::getMenuId));
        model.addAttribute("menuList", menuList);
        model.addAttribute("imageMap", imageMap);
        return "admin/menu/menu";
    }

    // 상품 수정
    @GetMapping("menu/{id}")
    public String menuDetail(@PathVariable int id, Model model,
        RedirectAttributes redirectAttributes) {
        MenuDTO menuDTO = menuService.getMenuById(id).orElse(null);
        if (menuDTO == null) {
            redirectAttributes.addFlashAttribute("msg", "존재하지 않는 메뉴입니다.");
            return "redirect:/admin/menu";
        }
        MenuRegistForm form = new MenuRegistForm();
        form.setMenuId(menuDTO.getId());
        form.setName(menuDTO.getName());
        form.setInfo(menuDTO.getInfo());
        form.setPrice(menuDTO.getPrice());
        form.setAmount(menuDTO.getAmount());

        List<MenuImageDTO> imageList = imageService.getMenuImageList(id);

        model.addAttribute("menuRegistForm", form);
        model.addAttribute("imageList", imageList);
        return "admin/menu/menu-detail";
    }

    @PostMapping("menu/{id}")
    public String menuUpdate(
        @PathVariable int id,
        @Valid MenuRegistForm form,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/menu/" + id;
        }
        form.setMenuId(id);
        menuService.updateMenu(form.getThumbnail(), form.toDto());
        return "redirect:/admin/menu";
    }

    @DeleteMapping("menu/{id}")
    public ResponseEntity<?> menuDelete(@PathVariable int id) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok().build();
    }

    // 상품 등록
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
