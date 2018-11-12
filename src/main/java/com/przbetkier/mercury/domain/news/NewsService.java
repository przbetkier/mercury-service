package com.przbetkier.mercury.domain.news;

import com.przbetkier.mercury.adapter.api.response.NewsResponse;
import com.przbetkier.mercury.news.NewsApiResponse;

import org.springframework.stereotype.Service;

@Service
public class NewsService {

    private final NewsClient newsClient;

    public NewsService(NewsClient newsClient) {
        this.newsClient = newsClient;
    }

    public NewsResponse getNews(String country, String category) {
        NewsApiResponse apiResponse = newsClient.getArticles(country, category);
        return NewsResponseMapper.map(apiResponse, country, category);
    }
}
