package com.grepp.gridncircle.app.model.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@Builder
public class MenuImageDto {
    @NotNull
    private int id;

    @Builder.Default
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @NotBlank
    @Size(max = 100)
    private String originalName;

    @NotBlank
    @Size(max = 100)
    private String renameName;

    @NotBlank
    @Size(max = 100)
    private String savePath;

    @NotNull
    private int menuId;

    public String getUrl() {
         return "/upload" + savePath + originalName; //return "/download/" + savePath + renameName; 에서 변경
    }

    public String getRenamedUrl() {
        return "/download/" + savePath + renameName;
    }
}