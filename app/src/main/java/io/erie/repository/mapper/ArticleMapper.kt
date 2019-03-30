package io.erie.repository.mapper

import io.erie.commons.utils.ReadTime
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article

class ArticleMapper {

    companion object : EntityMapper<Article, ArticleEntity> {
        override fun mapFromResponse(type: Article): ArticleEntity {
            return ArticleEntity(
                thumbnailUrl = type.urlToImage!!,
                title = type.title,
                authorName = type.author!!,
                sourceName = type.source!!.name!!,
                publishedAt = type.publishedAt!!,
                readTimeInMinutes = ReadTime.calculate(type.content!!),
                articleUrl = type.url!!
            )
        }
    }

}