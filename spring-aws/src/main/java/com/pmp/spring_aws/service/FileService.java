package com.pmp.spring_aws.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pmp.spring_aws.cloud.ObjectStorage;
import com.pmp.spring_aws.model.File;
import com.pmp.spring_aws.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private ObjectStorage objectStorage;

    @Autowired
    private FileRepository fileRepository;

    public File upload(MultipartFile multipartFile) throws IllegalStateException, IOException {
        String fileName = multipartFile.getOriginalFilename();

        File file = new File(fileName);
        var fileRecord = this.fileRepository.save(file);

        Path tempPath = Files.createTempFile(
                "upload-",
                fileRecord.getId().toString());
        multipartFile.transferTo(tempPath);

        var bucketName = objectStorage.upload(
                tempPath, fileRecord.getId().toString());

        Files.delete(tempPath);

        fileRecord.setBucketName(bucketName);
        fileRecord.setCreatedAt(LocalDateTime.now());
        return fileRepository.save(fileRecord);
    }
}
