package com.example.team2_be.core.utils;

import java.util.Map;
import lombok.Data;
import org.apache.http.auth.Credentials;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloud.aws")
@Data
public class CloudProperties {
    private Map<String, String> region;
    private Map<String, String> credentials;
}
