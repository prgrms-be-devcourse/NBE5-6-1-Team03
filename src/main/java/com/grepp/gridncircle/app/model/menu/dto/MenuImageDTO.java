package com.grepp.gridncircle.app.model.menu.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

import jakarta.validation.constraints.*;

@Data
@Builder
public class MenuImageDTO {
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
}