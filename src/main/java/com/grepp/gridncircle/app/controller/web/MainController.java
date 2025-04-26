package com.grepp.gridncircle.app.controller.web;

import com.grepp.gridncircle.app.model.menu.ImageService;
import com.grepp.gridncircle.app.model.menu.MenuService;
import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/","main"})
@RequiredArgsConstructor
public class MainController {

    private final MenuService menuService;
    private final ImageService imageService;

    @GetMapping
    public String searchMain(Model model) {
        List<MenuDto> popularMenus = menuService.getPopularMenus();
        List<MenuImageDto> imageList = imageService.getAllImage(); // 이미지 리스트 출력

        Map<Integer, List<MenuImageDto>> imageMap = imageList.stream()
                .collect(Collectors.groupingBy(MenuImageDto::getMenuId));

        model.addAttribute("imageMap", imageMap);
        model.addAttribute("popularMenus", popularMenus);
        model.addAttribute("images", imageList);

        return "main/main";
    }

    // 500번 에러페이지 데모용
    @GetMapping("500")
    public String transferServerError() {

        int a = 1/0;

        return "main/main";
    }





}
