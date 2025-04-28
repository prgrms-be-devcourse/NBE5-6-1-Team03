package com.grepp.gridncircle.app.controller.web.admin;

import com.grepp.gridncircle.app.controller.web.menu.form.MenuRegistForm;
import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import com.grepp.gridncircle.app.model.order.OrderService;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderGroupDto;
import com.grepp.gridncircle.app.model.order.dto.OrderSalesDto;
import com.grepp.gridncircle.app.model.order.dto.OrderStatsDto;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final MenuService menuService;
    private final ImageService imageService;
    private final OrderService orderService;

    // 관리자 메인페이지
    @GetMapping
    public String dashboard(
        @RequestParam(required = false) LocalDate startDate,
        @RequestParam(required = false) LocalDate endDate,
        Model model
    ) {
        List<OrderStatsDto> orderStatsList = null;
        List<OrderSalesDto> orderSalesList = null;
        if (startDate != null && endDate != null) {
            orderStatsList = orderService.getTop5Menu(startDate, endDate);
            orderSalesList = orderService.getDailySales(startDate, endDate);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
        } else {
            LocalDate from = LocalDate.now().minusDays(7);
            LocalDate to = LocalDate.now();
            orderStatsList = orderService.getTop5Menu(from, to);
            orderSalesList = orderService.getDailySales(from, to);
            model.addAttribute("startDate", from);
            model.addAttribute("endDate", to);
        }
        model.addAttribute("orderStatsList", orderStatsList);
        model.addAttribute("orderSalesList", orderSalesList);
        return "admin/dashboard";
    }

    // 주문 조회
    @GetMapping("orders")
    public String orders(
        @RequestParam(value = "orderDate", required = false) LocalDate orderDate,
        @RequestParam(value = "orderDateTime", required = false) LocalDateTime orderDateTime,
        Model model
    ) {
        List<OrderGroupDto> orderGroupList = null;
        if (orderDateTime != null) { // 주문일시를 받았을 때
            orderGroupList = orderService.getTimeZoneOrderList(orderDateTime);
            LocalDate baseDate = orderService.getBaseDate(orderDateTime);
            model.addAttribute("date", baseDate);
        } else { // 주문일을 받았을 때
            if (orderDate == null) {
                orderGroupList = orderService.getTodayOrderList();
                model.addAttribute("date", LocalDate.now());
            } else {
                orderGroupList = orderService.getDDayOrderList(orderDate);
                model.addAttribute("date", orderDate);
            }
        }
        model.addAttribute("orderGroupList", orderGroupList);
        return "admin/order/order";
    }

    // 주문 상세조회
    @GetMapping("orders/{orderId}")
    public String orderDetail(
        @PathVariable int orderId,
        @RequestParam LocalDateTime orderDateTime,
        Model model
    ) {
        OrderGroupDto orderGroup = orderService.getOrderByIdAndDateTIme(orderId, orderDateTime);
        log.info("orderGroup : {}", orderGroup);
        model.addAttribute("orderGroup", orderGroup);
        return "admin/order/order-detail";
    }

    // 주문 상태변경
    @PostMapping("orders/{orderId}")
    public String updateOrderStatus(
        @PathVariable int orderId,
        @RequestParam OrderStatus orderStatus,
        @RequestParam LocalDateTime orderDateTime,
        RedirectAttributes redirectAttributes
    ) {
        orderService.updateOrderStatus(orderId, orderDateTime, orderStatus);
        redirectAttributes.addFlashAttribute("msg", "주문 상태가 변경되었습니다.");
        return "redirect:/admin/orders?orderDateTime=" + orderDateTime;
    }

    // 그룹 발송처리
    @PostMapping("orders/status")
    public String orderGroupRelease(
        @RequestParam String status,
        @RequestParam String orderUserEmail,
        @RequestParam LocalDateTime orderDateTime,
        RedirectAttributes redirectAttributes
    ) {
        orderService.updateOrderGroupStatus(orderUserEmail, orderDateTime, status);
        redirectAttributes.addFlashAttribute("msg", "주문 상태가 변경되었습니다.");
        LocalDate baseDate = orderService.getBaseDate(orderDateTime);
        return "redirect:/admin/orders?orderDate=" + baseDate;
    }

    // 메뉴 전체 조회
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

    // 메뉴 상세 조회
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

    // 메뉴 수정
    @PostMapping("menu/{id}")
    public String menuUpdate(
        @PathVariable int id,
        @Valid MenuRegistForm form,
        BindingResult bindingResult
    ) {
        if (form.getThumbnail() != null) {
            for (MultipartFile file : form.getThumbnail()) {
                if (file.getOriginalFilename() != null && file.getOriginalFilename().length() > 100) { // 예: 100자 초과 금지
                    bindingResult.reject("thumbnail", "파일 이름이 너무 깁니다 (최대 100자).");
                }
            }
        }
        if (bindingResult.hasErrors()) {
            return "admin/menu/menu-detail";
        }
        form.setMenuId(id);
        menuService.updateMenu(form.getThumbnail(), form.toDto());
        return "redirect:/admin/menu";
    }

    // 메뉴 삭제
    @DeleteMapping("menu/{id}")
    public ResponseEntity<?> menuDelete(@PathVariable int id) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok().build();
    }

    // 메뉴 등록
    @GetMapping("menu/new")
    public String menuAdd(MenuRegistForm form) {
        return "admin/menu/menu-new";
    }

    @PostMapping("menu/new")
    public String menuRegist(
        @Valid MenuRegistForm form,
        BindingResult bindingResult
    ) {
        if (form.getThumbnail() != null) {
            for (MultipartFile file : form.getThumbnail()) {
                if (file.getOriginalFilename() != null && file.getOriginalFilename().length() > 100) { // 예: 100자 초과 금지
                    bindingResult.reject("thumbnail", "파일 이름이 너무 깁니다 (최대 100자).");
                }
            }
        }
        if (bindingResult.hasErrors()) {
            return "admin/menu/menu-new";
        }

        menuService.registMenu(form.getThumbnail(), form.toDto());
        return "redirect:/admin/menu";
    }
}
