package com.ceeblue.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdkApplication.class, args);
    }

    @Bean
    public RestTemplate getTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
