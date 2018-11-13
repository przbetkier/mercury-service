package com.przbetkier.mercury.domain.news;

import com.przbetkier.mercury.config.HttpClientProperties;
import com.przbetkier.mercury.infrastructure.NewsApiClientException;
import com.przbetkier.mercury.infrastructure.NewsApiServerErrorException;
import com.przbetkier.mercury.news.NewsApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NewsClient {

    private static final Logger logger = LoggerFactory.getLogger(NewsClient.class);

    private final HttpClientProperties httpClientProperties;
    private final RestTemplate restTemplate;

    public NewsClient(HttpClientProperties httpClientProperties,
            RestTemplate restTemplate) {
        this.httpClientProperties = httpClientProperties;
        this.restTemplate = restTemplate;
    }

    @Retryable(value = NewsApiServerErrorException.class, backoff = @Backoff(delay = 2000))
    NewsApiResponse getArticles(String country, String category) {
        try {
            return restTemplate.exchange(getUri(country, category), HttpMethod.GET, getEntity(), NewsApiResponse.class).getBody();
        } catch (HttpServerErrorException | ResourceAccessException serverException) {
            logger.warn("News api service is unavailable. Retrying...");
            throw new NewsApiServerErrorException("News api service is unavailable.");
        } catch (HttpClientErrorException ex) {
            throw new NewsApiClientException();
        }
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
