package com.pmp.spring_aws.cloud;

import java.nio.file.Path;

public interface ObjectStorage {

    String upload(Path path, String fileName);
}
