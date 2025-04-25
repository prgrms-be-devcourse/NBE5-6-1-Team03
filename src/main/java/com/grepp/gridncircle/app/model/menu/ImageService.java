package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    // 모든 이미지 리스트로 출력
    public List<MenuImageDTO> getAllImage() {
        return imageRepository.selectAllImage();
    }

    // menu.id == menu_img.id 만 출력 (1:1인 경우)
    public Optional<MenuImageDTO> getMenuImageById(@RequestParam("id") int menuId) {
        return imageRepository.selectMenuImage(menuId);
    }

    // menu.id == menu_img.id 인 이미지 리스트 출력 (1:N인 경우)
    public List<MenuImageDTO> getMenuImageList(@RequestParam("id") int menuId) {
        return imageRepository.selectMenuImageList(menuId);
    }
}
