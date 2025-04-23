package com.grepp.gridncircle.app.model.menu.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class MenuImgageDTO {
    private int id;
    private Timestamp createdAt;
    private String originalName;
    private String renameName;
    private String savePath;
    private int menuId;  // 메뉴와의 관계를 맺는 외래키
}