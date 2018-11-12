package com.przbetkier.mercury.news

import java.time.LocalDateTime

data class Article(
    val author: String?,
    val title: String,
    val description: String?,
    val date: LocalDateTime,
    val sourceName: String,
    val articleUrl: String,
    val imageUrl: String?
)