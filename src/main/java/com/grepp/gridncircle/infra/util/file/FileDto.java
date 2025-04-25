package com.grepp.gridncircle.infra.util.file;

public record FileDto (
    String originalName,
    String renameName,
    String savePath
) {
}
