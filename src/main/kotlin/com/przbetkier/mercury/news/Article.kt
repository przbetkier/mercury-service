package com.przbetkier.mercury.news

data class Article(
    val author: String?,
    val title: String,
    val description: String?,
    val date: String,
    val sourceName: String,
    val articleUrl: String,
    val imageUrl: String?
)