package com.grepp.gridncircle.app.controller.web;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

//    @Autowired
//    private MenuService menuService;


    @GetMapping
    public String searchmenu() {
//        List<MenuDTO>  menuList = menuService.getMenuList();
//        model.addAttribute("menuList", menuList);
        return "menu/menu";  // menu.jsp로 리턴
    }







}
