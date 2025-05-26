package com.pmp.spring_aws.cloud;

import java.nio.file.Path;

public interface ObjectStorage {

    void upload(Path path, String fileName, String bucketName);

    void download(Path path, String fileName, String bucketName);
}
