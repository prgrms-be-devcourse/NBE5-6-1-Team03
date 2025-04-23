package com.grepp.gridncircle.infra.util.file;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;

public record FileDto (
    String originalName,
    String renameName,
    String savePath
) {
}
