package com.grepp.gridncircle.app.controller.web.menu;


import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import java.util.List;
import java.util.Optional;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping
    public String searchmenu(Model model) {
        List<MenuDTO> menuList = menuService.getMenuList(); // 메뉴 리스트 출력
        List<MenuImageDTO> imageList = imageService.getAllImage(); // 이미지 리스트 출력

        model.addAttribute("menus", menuList);
        model.addAttribute("images", imageList);
        model.addAttribute("uploadPath", uploadPath);
        return "menu/menu";
    }


    @GetMapping("/detail")
    public String menuDetail(@RequestParam("id") int menuId, Model model) {
        Optional<MenuDTO> menu = menuService.getMenuById(menuId);
        MenuDTO menudetail = menu.orElse(null);
        // 메뉴 리스트 출력
        List<MenuImageDTO> imageList = imageService.getMenuImage(menuId); // 이미지 리스트 출력
        model.addAttribute("menudetail", menudetail);
        model.addAttribute("imageList", imageList);
        return "menu/menudetail";
    }






}
