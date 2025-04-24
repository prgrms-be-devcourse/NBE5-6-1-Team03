package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import com.grepp.gridncircle.infra.util.file.FileDto;
import com.grepp.gridncircle.infra.util.file.FileUtil;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {

    private final MenuRepository menuRepository;
    private FileUtil fileUtil;

    public List<MenuDTO> getMenuList() {
        return menuRepository.selectMenuList();
    }

    public Optional<MenuDTO> getMenuById(@RequestParam("id") int menuId) {
        return menuRepository.selectById(menuId);
    }

    @Transactional
    public void registMenu(List<MultipartFile> thumbnail, MenuDTO menuDTO) {
        try {
            List<FileDto> fileDtos = fileUtil.upload(thumbnail, "menu");
            menuRepository.insert(menuDTO);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
