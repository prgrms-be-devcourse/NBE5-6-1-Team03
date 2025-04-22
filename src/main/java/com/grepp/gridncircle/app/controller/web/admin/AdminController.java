package com.grepp.gridncircle.app.controller.web.admin;

import com.grepp.gridncircle.app.model.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("order")
    public String order() {
        return "admin/order";
    }
}
