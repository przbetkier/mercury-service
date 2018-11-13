package com.przbetkier.mercury.domain.news;

import com.przbetkier.mercury.infrastructure.InvalidCategoryException;

public enum NewsCategory {

    BUSINESS, ENTERTAINMENT, HEALTH, SCIENCE, SPORTS, TECHNOLOGY;

    public static NewsCategory fromCategory(String category) {
        try {
            return NewsCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new InvalidCategoryException(String.format("%s is not a valid category!", category));
        }
    }
}
