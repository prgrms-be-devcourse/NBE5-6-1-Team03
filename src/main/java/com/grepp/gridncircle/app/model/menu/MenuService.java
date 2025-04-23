package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import com.grepp.gridncircle.infra.util.file.FileDto;
import com.grepp.gridncircle.infra.util.file.FileUtil;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

            if (fileDtos.isEmpty()) return;
            for (FileDto fileDto : fileDtos) {
                MenuImageDTO menuImageDTO = MenuImageDTO.builder()
                    .originalName(fileDto.originalName())
                    .renameName(fileDto.renameName())
                    .savePath(fileDto.savePath())
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .menuId(menuDTO.getId())
                    .build();
                menuDAO.insertImage(menuImageDTO);
            }
        } catch (IOException e) { // 파일 이름이 너무 길 경우 오류 발생 가능
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
