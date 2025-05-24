package com.pmp.spring_aws.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

@Configuration
public class AwsConfiguration {

    @Value("${aws.region}")
    private String region;

    @Bean
    S3AsyncClient s3Client() {
        return S3AsyncClient.builder()
                .region(Region.of(region))
                .build();
    }

    @Bean
    S3TransferManager s3TransferManager(S3AsyncClient s3Client) {
        return S3TransferManager.builder()
                .s3Client(s3Client)
                .build();
    }
}
