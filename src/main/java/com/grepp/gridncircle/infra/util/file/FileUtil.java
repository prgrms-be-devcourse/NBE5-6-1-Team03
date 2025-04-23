package com.grepp.gridncircle.infra.util.file;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

    @Value("${upload.path}")
    private String filePath;

    public List<FileDto> upload(List<MultipartFile> files, String depth) throws IOException {
        List<FileDto> fileDtos = new ArrayList<>();
        String savePath = createSavePath(depth);

        for (MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            String renameName = generateRenameFileName(originalName);
            FileDto fileDto = new FileDto(originalName, renameName, savePath);
            fileDtos.add(fileDto);
            uploadFile(file, fileDto);
        }
        return fileDtos;
    }

    private void uploadFile(MultipartFile file, FileDto fileDto) throws IOException {
        File path = new File(filePath + fileDto.savePath());
        if (!path.exists()) {
            path.mkdirs();
        }
        File target = new File(filePath + fileDto.savePath() + fileDto.renameName());
        file.transferTo(target);
    }

    private String generateRenameFileName(String originalName) {
        String ext = originalName.substring(originalName.lastIndexOf("."));
        return UUID.randomUUID() + ext;
    }

    private String createSavePath(String depth) {

        LocalDate now = LocalDate.now();
        return depth + "/"
            + now.getYear() + "/"
            + now.getMonth() + "/"
            + now.getDayOfMonth() + "/";

    }
}
