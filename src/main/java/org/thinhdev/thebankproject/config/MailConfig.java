package org.thinhdev.thebankproject.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {
    private String username;
    private String host;
    private int port;
    private String password;
}
