package com.hadef.loguxgaming.service.impl;

import com.hadef.loguxgaming.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String fileName = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString(); // Prevent path traversal
        Path fullPath = Paths.get(path).resolve(fileName);

        Files.createDirectories(fullPath.getParent()); // Creates path if it doesn't exist
        Files.copy(file.getInputStream(), fullPath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator+fileName;
        return new FileInputStream(filePath);
    }

    @Override
    public void deleteFile(String path, String fileName) throws FileNotFoundException {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new RuntimeException("Failed to delete file: " + filePath);
            }
        } else {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    @Override
    public String updateFile(String path, MultipartFile file, String image) throws IOException {
        deleteFile(path, image);
        return uploadFile(path, file);
    }
}
