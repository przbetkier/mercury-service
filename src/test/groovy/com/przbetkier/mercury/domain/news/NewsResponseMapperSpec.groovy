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
        result.articles.first().date == "2018-11-10"
        result.articles.first().author == article.author
        result.articles.first().articleUrl == article.url
        result.articles.first().imageUrl == article.urlToImage
        result.articles.first().title == article.title
        result.articles.first().sourceName == article.source.name
        result.articles.first().description == article.description
    }
}
