package com.przbetkier.mercury.domain.news

import com.przbetkier.mercury.infrastructure.NewsCategoryException
import spock.lang.Specification
import spock.lang.Unroll

class NewsCategorySpec extends Specification {

    @Unroll
    def "should not throw an exception for valid category #category"() {
        when:
        def result = NewsCategory.fromCategory(category)

        then:
        noExceptionThrown()
        result == expectedResult

        where:
        category        || expectedResult
        "technology"    || NewsCategory.TECHNOLOGY
        "entertainment" || NewsCategory.ENTERTAINMENT
        "science"       || NewsCategory.SCIENCE
        "health"        || NewsCategory.HEALTH
        "business"      || NewsCategory.BUSINESS
        "sports"        || NewsCategory.SPORTS
    }

    @Unroll
    def "should throw an exception for invalid category #category"() {
        when:
        NewsCategory.fromCategory(category)

        then:
        thrown NewsCategoryException

        where:
        category << ["games", "movies", "programming", "celebrities"]
    }
}
