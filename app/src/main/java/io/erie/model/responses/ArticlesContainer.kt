package io.erie.model.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesContainer(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article>?
)