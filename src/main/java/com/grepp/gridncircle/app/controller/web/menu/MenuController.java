package com.grepp.gridncircle.app.controller.web.menu;


import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;
    private final ImageService imageService;




    @GetMapping
    public String searchmenu(Model model) {
        List<MenuDTO> menuList = menuService.getMenuList(); // 메뉴 리스트 출력
        List<MenuImageDTO> imageList = imageService.getAllImage(); // 이미지 리스트 출력

        Map<Integer, List<MenuImageDTO>> imageMap = imageList.stream()
                .collect(Collectors.groupingBy(MenuImageDTO::getMenuId));

        model.addAttribute("imageMap", imageMap);
        model.addAttribute("menus", menuList);
        model.addAttribute("images", imageList);

        return "menu/menu";
    }


    @GetMapping("/detail")
    public String menuDetail(@RequestParam("id") int menuId, Model model) {
        Optional<MenuDTO> menuDetail = menuService.getMenuById(menuId);// 메뉴 출력
        MenuDTO menu = menuDetail.orElse(null);

        Optional<MenuImageDTO> imageDetail = imageService.getMenuImageById(menuId); // 메뉴 이미지 출력
        MenuImageDTO image = imageDetail.orElse(null);

        model.addAttribute("menu", menu);
        model.addAttribute("image", image);
        return "menu/menudetail";
    }






}
