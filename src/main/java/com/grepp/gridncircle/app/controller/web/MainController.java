package com.grepp.gridncircle.app.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","main"})
public class MainController {
    @GetMapping
    public String searchMain() {
        return "main/main";
    }

    // 500번 에러페이지 데모용
    @GetMapping("500")
    public String transferServerError() {

        int a = 1/0;

        return "main/main";
    }
}
