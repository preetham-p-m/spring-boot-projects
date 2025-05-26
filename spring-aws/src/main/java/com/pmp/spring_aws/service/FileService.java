package com.pmp.spring_aws.service;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.pmp.spring_aws.cloud.ObjectStorage;
import com.pmp.spring_aws.model.File;
import com.pmp.spring_aws.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private ObjectStorage objectStorage;

    @Autowired
    private FileRepository fileRepository;

    @Value("${aws.s3-bucket-name}")
    private String bucketName;

    public ResponseEntity<File> upload(MultipartFile multipartFile) throws IllegalStateException, IOException {
        String fileName = multipartFile.getOriginalFilename();

        File file = new File(fileName);
        var fileRecord = this.fileRepository.save(file);

        Path tempFile = Files.createTempFile(
                "upload-",
                fileRecord.getId().toString());
        multipartFile.transferTo(tempFile);

        objectStorage.upload(
                tempFile, fileRecord.getId().toString(), bucketName);

        Files.delete(tempFile);

        fileRecord.setBucketName(bucketName);
        fileRecord.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(fileRepository.save(fileRecord));
    }

    public ResponseEntity<InputStreamResource> downloadFile(UUID fileId) throws IOException {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new NoSuchElementException("File not found with id: " + fileId));

        Path temFile = Files.createTempFile("download-", file.getFileName());

        objectStorage.download(temFile,
                fileId.toString(),
                file.getBucketName());

        InputStream deletingStream = new FilterInputStream(Files.newInputStream(temFile)) {
            @Override
            public void close() throws IOException {
                super.close();
                try {
                    Files.deleteIfExists(temFile);
                } catch (IOException e) {
                    // log error if needed
                    e.printStackTrace();
                }
            }
        };

        InputStreamResource resource = new InputStreamResource(deletingStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                .contentLength(Files.size(temFile))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
