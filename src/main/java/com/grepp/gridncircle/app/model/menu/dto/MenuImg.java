package com.grepp.gridncircle.app.model.menu.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MenuImg {
    private int id;
    private int menuId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String originalName;
    private String renameName;
    private String savePath;
}
