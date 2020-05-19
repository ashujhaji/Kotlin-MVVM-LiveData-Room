package com.byju.assignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class TopHeadlineResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

@Entity(tableName = "ArticleTable")
data class Article(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    @PrimaryKey(autoGenerate = false)
    val publishedAt: String,
    val content: String?
)

data class Source(
    val name: String
)