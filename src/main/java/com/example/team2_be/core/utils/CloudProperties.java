package com.example.team2_be.core.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud.aws")
@Data
public class CloudProperties {
    private String s3Bucket = "kakaotechcampust-step3-nemobucket";
    private boolean stackAuto = false;
    private String regionStatic = "ap-northeast-2";
    private String credentialsAccessKey = "AKIAVBQDJ37FAVI2YLP2";
    private String credentialsSecretKey = "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";
}
