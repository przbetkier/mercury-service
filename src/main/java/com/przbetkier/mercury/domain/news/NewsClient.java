package com.przbetkier.mercury.domain.news;

import com.przbetkier.mercury.config.HttpClientProperties;
import com.przbetkier.mercury.news.NewsApiResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NewsClient {

    private final HttpClientProperties httpClientProperties;

    private final RestTemplate restTemplate;

    public NewsClient(HttpClientProperties httpClientProperties,
            RestTemplate restTemplate) {
        this.httpClientProperties = httpClientProperties;
        this.restTemplate = restTemplate;
    }

    NewsApiResponse getArticles(String country, String category) {
        return restTemplate.exchange(getUri(country, category), HttpMethod.GET, getEntity(), NewsApiResponse.class).getBody();
        // TODO: Error Handling
    }

    private URI getUri(String country, String category) {
        return UriComponentsBuilder
                .fromUriString(httpClientProperties.getApiUrl())
                .queryParam("country", country)
                .queryParam("category", category)
                .build()
                .toUri();
    }

    private HttpEntity<String> getEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Api-Key", httpClientProperties.getApiKey());
        return new HttpEntity<>(httpHeaders);
    }
}
