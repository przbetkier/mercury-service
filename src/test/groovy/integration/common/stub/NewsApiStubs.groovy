package integration.common.stub

import com.github.tomakehurst.wiremock.stubbing.Scenario
import integration.common.response.SampleNewsApiResponse

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching
import static org.springframework.http.HttpHeaders.CONTENT_TYPE
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

class NewsApiStubs {

    static void successfulResponse(String country, String category) {
        stubFor(get(urlEqualTo("/v2/top-headlines?country=${country}&category=${category}"))
                .willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(SampleNewsApiResponse.simple())
        ))
    }

    static void serviceUnavailableError(String country, String category) {
        stubFor(get(urlEqualTo("/v2/top-headlines?country=${country}&category=${category}"))
                .willReturn(
                aResponse()
                        .withStatus(503)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        ))
    }

    static def serviceTimeoutThenSuccessful(String country, String category) {
        stubFor(get(urlEqualTo("/v2/top-headlines?country=${country}&category=${category}"))
                .inScenario("newsapi-timeout-retry")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(
                aResponse()
                        .withFixedDelay(200)
                        .withHeader(CONTENT_TYPE, "application/json"))
                .willSetStateTo("timeout-occurred"))

        stubFor(get(urlEqualTo("/v2/top-headlines?country=${country}&category=${category}"))
                .inScenario("newsapi-timeout-retry")
                .whenScenarioStateIs("timeout-occurred")
                .willReturn(
                aResponse()
                        .withHeader(CONTENT_TYPE, "application/json")
                        .withBody(SampleNewsApiResponse.simple())))
    }
}
