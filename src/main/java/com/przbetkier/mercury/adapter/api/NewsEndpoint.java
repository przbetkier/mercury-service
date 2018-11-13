package com.przbetkier.mercury.adapter.api;

import com.przbetkier.mercury.adapter.api.response.NewsResponse;
import com.przbetkier.mercury.domain.news.NewsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
class NewsEndpoint {

    private final NewsService newsService;

    NewsEndpoint(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{country}/{category}")
    public NewsResponse getNews(@PathVariable String country, @PathVariable String category) {
        return newsService.getNews(country, category);
    }
}
