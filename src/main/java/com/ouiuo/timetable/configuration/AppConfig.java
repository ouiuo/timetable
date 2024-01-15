package com.ouiuo.timetable.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @PostConstruct
    private void setUp() {
        System.setProperty("com.sun.security.enableAIAcaIssuers", "true");
    }
}
