package com.przbetkier.mercury.domain.news;

import static com.przbetkier.mercury.util.IsoDateProvider.formatToIsoDate;
import static com.przbetkier.mercury.domain.news.NewsCategory.fromCategory;
import static java.util.stream.Collectors.toList;

import com.przbetkier.mercury.adapter.api.response.NewsResponse;
import com.przbetkier.mercury.news.Article;
import com.przbetkier.mercury.news.NewsApiResponse;

import org.apache.commons.lang.StringEscapeUtils;

class NewsResponseMapper {

    private NewsResponseMapper() {
    }

    static NewsResponse map(NewsApiResponse newsApiResponse, String country, String category) {
        return new NewsResponse(
                country,
                fromCategory(category),
                newsApiResponse.getArticles().stream().map(
                        articleDto -> new Article(
                                articleDto.getAuthor(),
                                articleDto.getTitle(),
                                StringEscapeUtils.unescapeJava(articleDto.getDescription()),
                                formatToIsoDate(articleDto.getPublishedAt().toLocalDateTime()),
                                articleDto.getSource().getName(),
                                articleDto.getUrl(),
                                articleDto.getUrlToImage()
                        )
                ).collect(toList())
        );
    }
}
