package com.przbetkier.mercury.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
class HttpClientConfig {

    private final HttpClientProperties httpClientProperties;

    public HttpClientConfig(HttpClientProperties httpClientProperties) {
        this.httpClientProperties = httpClientProperties;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(createRequestFactory());
    }

    private ClientHttpRequestFactory createRequestFactory() {
        OkHttp3ClientHttpRequestFactory okHttpClientHttpRequestFactory = new OkHttp3ClientHttpRequestFactory();
        okHttpClientHttpRequestFactory.setReadTimeout(httpClientProperties.getTimeout().getRead());
        okHttpClientHttpRequestFactory.setConnectTimeout(httpClientProperties.getTimeout().getConnect());
        return okHttpClientHttpRequestFactory;
    }
}
