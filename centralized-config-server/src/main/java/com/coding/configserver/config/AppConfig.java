package com.coding.configserver.config;

import org.springframework.cloud.config.monitor.GithubPropertyPathNotificationExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GithubPropertyPathNotificationExtractor githubPropertyPathNotificationExtractor() {
        return new GithubPropertyPathNotificationExtractor();
    }
}
