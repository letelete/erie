package io.erie.model.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    val title: String,
    val source: Source?,
    val author: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) {
    fun hasEssentialAttributes() = urlToImage != null
            && author != null
            && source?.name != null
            && content != null
            && publishedAt != null
            && url != null
}