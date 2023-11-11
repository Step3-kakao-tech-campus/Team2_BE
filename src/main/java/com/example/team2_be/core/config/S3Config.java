package com.example.team2_be.core.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.team2_be.core.utils.CloudProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    CloudProperties cloudProperties;

    @Autowired
    public S3Config(CloudProperties cloudProperties) {
        this.cloudProperties = cloudProperties;
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(cloudProperties.getCredentials().get("accessKey"),
                cloudProperties.getCredentials().get("secretKey"));
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(cloudProperties.getRegion().get("static"))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
