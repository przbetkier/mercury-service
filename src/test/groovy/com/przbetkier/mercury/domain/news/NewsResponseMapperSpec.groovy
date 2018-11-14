package com.przbetkier.mercury.domain.news

import com.przbetkier.mercury.common.SampleArticleDto
import com.przbetkier.mercury.common.SampleNewsApiResponse
import spock.lang.Specification

import java.time.ZoneId
import java.time.ZonedDateTime

class NewsResponseMapperSpec extends Specification {

    def "should map api response"() {
        given:
        def article = SampleArticleDto.simpleWithDate(ZonedDateTime.of(2018, 11, 10, 12, 0, 0, 0, ZoneId.systemDefault()))
        def apiResponse = SampleNewsApiResponse.simpleWithArticles([article])
        def country = "pl"
        def category = "technology"

        when:
        def result = NewsResponseMapper.map(apiResponse, country, category)

        then:
        noExceptionThrown()
        result.category == NewsCategory.TECHNOLOGY
        result.country == "pl"
        result.articles.size() == apiResponse.articles.size()
        with(result.articles.first()) {
            date == "2018-11-10"
            author == article.author
            articleUrl == article.url
            imageUrl == article.urlToImage
            title == article.title
            sourceName == article.source.name
            description == article.description
        }
    }
}
