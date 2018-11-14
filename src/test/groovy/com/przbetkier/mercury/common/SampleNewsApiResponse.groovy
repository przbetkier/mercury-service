package com.przbetkier.mercury.common

import com.przbetkier.mercury.news.ArticleDto
import com.przbetkier.mercury.news.NewsApiResponse

class SampleNewsApiResponse {

    static def simpleWithArticles(List<ArticleDto> articles) {
        return new NewsApiResponse(
                "ok",
                1,
                articles
        )
    }
}
