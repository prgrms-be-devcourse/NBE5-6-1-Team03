package com.grepp.gridncircle.app.controller.web.menu;


import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;
    private final ImageService imageService;


    @GetMapping
    public String searchmenu(Model model) {
        List<MenuDto> menuList = menuService.getMenuList(); // 메뉴 리스트 출력
        List<MenuImageDto> imageList = imageService.getAllImage(); // 이미지 리스트 출력

        Map<Integer, List<MenuImageDto>> imageMap = imageList.stream()
                .collect(Collectors.groupingBy(MenuImageDto::getMenuId));

        model.addAttribute("imageMap", imageMap);
        model.addAttribute("menus", menuList);
        model.addAttribute("images", imageList);

        return "menu/menu";
    }


    @GetMapping("/detail")
    public String menuDetail(@RequestParam("id") int menuId, Model model) {
        Optional<MenuDto> menuDetail = menuService.getMenuById(menuId);// 메뉴 출력
        MenuDto menu = menuDetail.orElse(null);

        Optional<MenuImageDto> imageDetail = imageService.getMenuImageById(menuId); // 메뉴 이미지 출력
        MenuImageDto image = imageDetail.orElse(null);

        model.addAttribute("menu", menu);
        model.addAttribute("image", image);
        return "menu/menudetail";
    }








}
