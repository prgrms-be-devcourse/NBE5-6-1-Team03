package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    public List<MenuDTO> getMenuList(){
        return menuDAO.selectMenuList();
    }

    public void registMenu(List<MultipartFile> thumbnail, MenuDTO menuDTO) {
        menuDAO.insert(menuDTO);
    }
}
