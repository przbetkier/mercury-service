package com.przbetkier.mercury.domain.news;

public enum NewsCategory {

    BUSINESS, ENTERTAINMENT, HEALTH, SCIENCE, SPORTS, TECHNOLOGY;

    public static NewsCategory fromCategory(String category) {
        return NewsCategory.valueOf(category.toUpperCase());
    }
}
