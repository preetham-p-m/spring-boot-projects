package com.pmp.spring_aws.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pmp.spring_aws.model.File;
import com.pmp.spring_aws.service.FileService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<File> upload(@RequestParam MultipartFile multipartFile)
            throws IllegalStateException, IOException {
        return fileService.upload(multipartFile);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<InputStreamResource> getMethodName(@PathVariable("fileId") UUID fileId) throws IOException {
        return fileService.downloadFile(fileId);
    }
}
