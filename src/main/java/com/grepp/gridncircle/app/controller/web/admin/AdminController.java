package com.grepp.gridncircle.app.controller.web.admin;

import com.grepp.gridncircle.app.controller.web.menu.form.MenuRegistForm;
import com.grepp.gridncircle.app.model.admin.AdminService;
import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import com.grepp.gridncircle.app.model.order.OrderService;
import com.grepp.gridncircle.app.model.order.dto.OrderInfoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final OrderService orderService;

    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }

    // 주문 조회
    @GetMapping("orders")
    public String orders(
        @RequestParam(value = "date", required = false) String date,
        Model model
    ) {
        System.out.println(date);
        List<OrderInfoDto> orderInfoList = null;
        if (date == null || date.isEmpty()) {
            orderInfoList = orderService.getTodayOrderList();
            model.addAttribute("date", LocalDate.now());
        } else {
            LocalDate today = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            orderInfoList = orderService.getDDayOrderList(today);
            model.addAttribute("date", today);
        }
        model.addAttribute("orderInfoList", orderInfoList);
        return "admin/order/order";
    }

    @GetMapping("orders/{id}")
    public String orderDetail(@PathVariable int id, Model model) {
//        Order order = orderService.findById(id);
//        model.addAttribute("order", order);
        return "admin/order/order-detail";
    }

    // 상태변경
    @PostMapping("orders/{id}")
    public String updateOrderStatus(
        @PathVariable int id,
        @RequestParam String status,
        @RequestParam String date,
        RedirectAttributes redirectAttributes
    ) {
        orderService.updateStatus(id, status);
        redirectAttributes.addFlashAttribute("msg", "주문 상태가 변경되었습니다.");
        LocalDate dDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "redirect:/admin/orders?date=" + dDay;
    }

    // 상품 관리
    @GetMapping("menu")
    public String menu(Model model) {
        List<MenuDto> menuList = menuService.getMenuList();
        List<MenuImageDto> imageList = imageService.getAllImage();
        Map<Integer, List<MenuImageDto>> imageMap = imageList.stream()
            .collect(Collectors.groupingBy(MenuImageDto::getMenuId));
        model.addAttribute("menuList", menuList);
        model.addAttribute("imageMap", imageMap);
        return "admin/menu/menu";
    }

    // 상품 수정
    @GetMapping("menu/{id}")
    public String menuDetail(@PathVariable int id, Model model,
        RedirectAttributes redirectAttributes) {
        MenuDto menuDTO = menuService.getMenuById(id).orElse(null);
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

        List<MenuImageDto> imageList = imageService.getMenuImageList(id);

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
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "admin/menu/menu-new";
        }
        if (form.getThumbnail() == null || form.getThumbnail().isEmpty() ||
            form.getThumbnail().stream().allMatch(file -> file.isEmpty())) {
            redirectAttributes.addFlashAttribute("alertMessage", "사진을 등록해주세요.");
            return "redirect:/admin/menu/new";
        }

        menuService.registMenu(form.getThumbnail(), form.toDto());
        return "redirect:/admin/menu";
    }
}
