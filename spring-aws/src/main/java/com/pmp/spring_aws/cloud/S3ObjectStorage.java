package com.pmp.spring_aws.cloud;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

@Service
public class S3ObjectStorage implements ObjectStorage {

    @Autowired
    private S3TransferManager s3TransferManager;

    private String bucketName;

    @Override
    public String upload(Path path, String fileName) {
        UploadFileRequest uploadRequest = UploadFileRequest.builder()
                .putObjectRequest(
                        b -> b.bucket(bucketName)
                                .key(fileName))
                .source(path)
                .build();

        s3TransferManager.uploadFile(uploadRequest)
                .completionFuture()
                .join();

        return bucketName;
    }

}
