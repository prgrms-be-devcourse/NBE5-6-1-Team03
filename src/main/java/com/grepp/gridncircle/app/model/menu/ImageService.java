package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<MenuImageDTO> getAllImage() {
        return imageRepository.selectAllImage();
    }

    public List<MenuImageDTO> getMenuImage(@RequestParam("id") int menuId) {
        return imageRepository.selectMenuImage(menuId);
    }
}
