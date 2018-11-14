package com.przbetkier.mercury.util

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class IsoDateProviderSpec extends Specification {

    @Unroll
    def "should parse local date time to formatted string"() {
        expect:
        IsoDateProvider.formatToIsoDate(date) == expected

        where:
        date                                 || expected
        LocalDateTime.of(2016, 5, 2, 10, 10) || "2016-05-02"
        LocalDateTime.of(2018, 1, 1, 23, 59) || "2018-01-01"
        LocalDateTime.of(2018, 10, 10, 0, 0) || "2018-10-10"
    }
}
