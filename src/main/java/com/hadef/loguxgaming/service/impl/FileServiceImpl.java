package com.hadef.loguxgaming.service.impl;

import com.hadef.loguxgaming.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = path + File.separator+fileName;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
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
}
