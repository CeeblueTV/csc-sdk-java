package com.ceeblue.sdk;

import com.ceeblue.sdk.http.template.utils.RestTemplateResponseErrorHandler;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;

@SpringBootApplication
public class SdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdkApplication.class, args);
    }

    @Bean("okHttpRestTemplate")
    public RestTemplate ceeblueRestTemplate() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Duration.of(10000, MILLIS))
                .readTimeout(Duration.of(10000, MILLIS))
                .build();

        return new RestTemplate(new OkHttp3ClientHttpRequestFactory(client));
    }

    @Bean("ceeblueRestTemplate")
    public RestTemplate getTemplate(RestTemplateBuilder builder) {
        return builder.errorHandler(new RestTemplateResponseErrorHandler())
                .setConnectTimeout(Duration.of(10000, MILLIS))
                .setReadTimeout(Duration.of(10000, MILLIS))
                .build();
    }
}
