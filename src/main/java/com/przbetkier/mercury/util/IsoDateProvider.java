package com.przbetkier.mercury.util;

import static java.time.format.DateTimeFormatter.ISO_DATE;

import java.time.LocalDateTime;

public class IsoDateProvider {

    private IsoDateProvider() {
    }

    public static String formatToIsoDate(LocalDateTime date) {
        return ISO_DATE.format(date);
    }
}
