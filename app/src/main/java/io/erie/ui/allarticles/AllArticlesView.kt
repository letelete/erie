package io.erie.ui.allarticles

import androidx.paging.PagedList
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article

interface AllArticlesView {
    fun updateListContent(articles: PagedList<ArticleEntity>)
}