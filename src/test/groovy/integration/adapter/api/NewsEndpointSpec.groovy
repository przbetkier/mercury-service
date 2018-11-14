package integration.adapter.api

import com.github.tomakehurst.wiremock.client.WireMock
import integration.IntegrationSpec
import integration.common.stub.NewsApiStubs
import spock.lang.Unroll

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

class NewsEndpointSpec extends IntegrationSpec {

    def "should get news api response"() {
        given:
        def country = "pl"
        def category = "technology"
        NewsApiStubs.successfulResponse(country, category)

        when:
        def response = restTemplate.getForEntity(localUrl("/news/${country}/${category}"), Map)

        then:
        response.statusCodeValue == 200
        response.body.country == country
        response.body.category == category.toUpperCase()
        response.body.articles instanceof List
        response.body.articles.size() > 0
    }

    def "should retry on server error"() {
        given:
        def country = "gb"
        def category = "technology"
        NewsApiStubs.serviceUnavailableError(country, category)

        when:
        def response = restTemplate.getForEntity(localUrl("/news/${country}/${category}"), String)

        then:
        response.statusCodeValue == 500
        response.body == 'News api service is unavailable.'
        WireMock.verify(3, getRequestedFor(urlEqualTo("/v2/top-headlines?country=${country}&category=${category}")))
    }

    def "should retry on timeout and finish with successful response"() {
        given:
        def country = "pl"
        def category = "technology"
        NewsApiStubs.serviceTimeoutThenSuccessful(country, category)

        when:
        def response = restTemplate.getForEntity(localUrl("/news/${country}/${category}"), Map)

        then:
        response.statusCodeValue == 200
        response.body.country == country
        response.body.category == category.toUpperCase()
        response.body.articles.size() > 0
        WireMock.verify(2, getRequestedFor(urlEqualTo("/v2/top-headlines?country=${country}&category=${category}")))
    }

    @Unroll
    def "should respond with #statusCode for #category"() {
        given:
        def country = "gb"
        NewsApiStubs.successfulResponse(country, category)

        when:
        def response = restTemplate.getForEntity(localUrl("/news/${country}/${category}"), String)

        then:
        response.statusCodeValue == statusCode

        where:
        category     || statusCode
        "technology" || 200
        "health"     || 200
        "movies"     || 400
        "games"      || 400

    }
}
