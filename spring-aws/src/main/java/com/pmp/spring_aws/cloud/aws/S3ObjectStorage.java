package com.pmp.spring_aws.cloud.aws;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pmp.spring_aws.cloud.ObjectStorage;

import software.amazon.awssdk.transfer.s3.model.DownloadFileRequest;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

@Service
@Primary
public class S3ObjectStorage implements ObjectStorage {

        @Autowired
        private S3TransferManager s3TransferManager;

        @Override
        public void upload(Path path, String fileName, String bucketName) {
                UploadFileRequest uploadRequest = UploadFileRequest.builder()
                                .putObjectRequest(b -> b.bucket(bucketName).key(fileName))
                                .source(path)
                                .build();

                s3TransferManager.uploadFile(uploadRequest)
                                .completionFuture()
                                .join();

        }

        public void download(Path path, String fileName, String bucketName) {
                DownloadFileRequest downloadRequest = DownloadFileRequest.builder()
                                .getObjectRequest(b -> b.bucket(bucketName).key(fileName))
                                .destination(path)
                                .build();

                s3TransferManager.downloadFile(downloadRequest)
                                .completionFuture()
                                .join();
        }
}
