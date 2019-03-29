package io.erie.ui.topheadlines

import androidx.paging.PagedList
import io.erie.model.entities.ArticleEntity

interface TopHeadlinesView {
    fun updateListContent(articles: PagedList<ArticleEntity>)
}