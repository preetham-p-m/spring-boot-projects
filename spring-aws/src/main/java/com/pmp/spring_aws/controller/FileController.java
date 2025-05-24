package com.pmp.spring_aws.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pmp.spring_aws.model.File;
import com.pmp.spring_aws.service.FileService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("path")
    public File upload(@RequestParam MultipartFile multipartFile) throws IllegalStateException, IOException {
        return fileService.upload(multipartFile);
    }

}
