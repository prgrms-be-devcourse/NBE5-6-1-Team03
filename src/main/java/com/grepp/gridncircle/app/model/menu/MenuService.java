package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import com.grepp.gridncircle.infra.util.file.FileDto;
import com.grepp.gridncircle.infra.util.file.FileUtil;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {

    private final MenuRepository menuRepository;
    private final FileUtil fileUtil;

    public List<MenuDto> getPopularMenus() {
        return menuRepository.selectPopularMenuList();
    }

    public List<MenuDto> getMenuList() {
        return menuRepository.selectMenuList();
    }

    public Optional<MenuDto> getMenuById(@RequestParam("id") int menuId) {
        return menuRepository.selectById(menuId);
    }

    @Transactional
    public void registMenu(List<MultipartFile> thumbnail, MenuDto menuDTO) {
        try {
            menuRepository.insert(menuDTO);
            if (thumbnail != null && thumbnail.stream().anyMatch(file -> !file.isEmpty())) {
                List<FileDto> fileDtos = fileUtil.upload(thumbnail, "img");
                for (FileDto fileDto : fileDtos) {
                    MenuImageDto menuImageDTO = fileToImageDTO(fileDto, menuDTO.getId());
                    menuRepository.insertImage(menuImageDTO);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류 발생", e);
        }
    }

    @Transactional
    public void updateMenu(@NotNull List<MultipartFile> thumbnail, MenuDto menuDTO) {
        try {
            menuRepository.update(menuDTO);
            if (thumbnail != null && thumbnail.stream().anyMatch(file -> !file.isEmpty())) {
                List<FileDto> fileDtos = fileUtil.upload(thumbnail, "img");
                Optional<MenuImageDto> result = menuRepository.findImageById(menuDTO.getId());
                if (result.isPresent()) {
                    for (FileDto fileDto : fileDtos) {
                        MenuImageDto menuImageDTO = fileToImageDTO(fileDto, menuDTO.getId());
                        menuRepository.updateImage(menuImageDTO);
                    }
                } else {
                    for (FileDto fileDto : fileDtos) {
                        MenuImageDto menuImageDTO = fileToImageDTO(fileDto, menuDTO.getId());
                        menuRepository.insertImage(menuImageDTO);
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류 발생", e);
        }
    }

    @Transactional
    public void deleteMenu(int id) {
        menuRepository.deleteImageByMenuId(id);
        menuRepository.makeDeletedById(id);
    }

    private MenuImageDto fileToImageDTO(FileDto fileDto, int id) {
        return MenuImageDto.builder()
            .originalName(fileDto.originalName())
            .renameName(fileDto.renameName())
            .savePath(fileDto.savePath())
            .createdAt(Timestamp.valueOf(LocalDateTime.now()))
            .menuId(id)
            .build();
    }
}
