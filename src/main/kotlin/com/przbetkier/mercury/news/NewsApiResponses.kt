package com.przbetkier.mercury.news

import java.time.ZonedDateTime

typealias Content = String

data class NewsApiResponse(
    val status: String,
    val totalResults: Number,
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: ZonedDateTime,
    val content: Content?
)

data class Source(
    val id: String?,
    val name: String
)