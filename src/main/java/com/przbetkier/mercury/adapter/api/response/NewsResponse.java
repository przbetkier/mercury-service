package com.przbetkier.mercury.adapter.api.response;

import com.przbetkier.mercury.domain.news.NewsCategory;
import com.przbetkier.mercury.news.Article;

import java.util.List;

public class NewsResponse {

    private final String country;
    private final NewsCategory category;
    private final List<Article> articles;

    public NewsResponse(String country, NewsCategory category, List<Article> articles) {
        this.country = country;
        this.category = category;
        this.articles = articles;
    }

    public String getCountry() {
        return country;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
