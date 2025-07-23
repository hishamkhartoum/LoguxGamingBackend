package com.hadef.loguxgaming.controller;

import com.hadef.loguxgaming.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws IOException {
        String uploadedFile = fileService.uploadFile(path,file);
        return ResponseEntity.ok("File uploaded successfully "+uploadedFile);
    }

    @GetMapping("/{filename}")
    public void getFile(@PathVariable String filename, HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResourceFile(path,filename);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
