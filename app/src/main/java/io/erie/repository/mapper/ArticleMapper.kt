package io.erie.repository.mapper

import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article

class ArticleMapper {

    companion object : EntityMapper<Article, ArticleEntity> {
        private var articleContent: String? = null
        private var articlePublishedAt: String? = null

        override fun mapFromResponse(type: Article): ArticleEntity {
            articleContent = type.content
            articlePublishedAt = type.publishedAt
            return ArticleEntity(
                thumbnailUrl = type.urlToImage!!,
                title = type.title,
                authorName = type.author!!,
                sourceName = type.source!!.name!!,
                articleDetails = articleDetails(),
                articleUrl = type.url!!
            )
        }

        /**
         *  author details example: 2 days ago • 23 min read
         */
        private fun articleDetails(): String {
            // TODO:
            return articlePublishedAt!!
        }
    }

}