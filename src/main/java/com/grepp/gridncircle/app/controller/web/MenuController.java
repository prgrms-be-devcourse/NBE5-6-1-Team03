package com.grepp.gridncircle.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MenuController {

    @GetMapping("/menu")
    public String searchmenu() {
        return "menu/menu";
    }


    @GetMapping("/menudetail")
    public String searchmenudetail() {
        return "menu/menudetail";
    }
}
