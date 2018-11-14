package com.przbetkier.mercury.common

import com.przbetkier.mercury.news.ArticleDto
import com.przbetkier.mercury.news.Source

import java.time.ZonedDateTime

class SampleArticleDto {

    def static simpleWithDate(ZonedDateTime dateTime = ZonedDateTime.now()) {
        return new ArticleDto(
                new Source(null, "Spidersweb.pl"),
                "author",
                "Article title",
                "Simple description",
                "http://url.com",
                "http://url-to-image.com",
                dateTime,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eu eros diam."
        )
    }
}
