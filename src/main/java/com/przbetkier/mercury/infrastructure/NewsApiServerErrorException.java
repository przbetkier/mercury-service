package com.przbetkier.mercury.infrastructure;

public class NewsApiServerErrorException extends RuntimeException {
    public NewsApiServerErrorException(String message) {
        super(message);
    }
}
