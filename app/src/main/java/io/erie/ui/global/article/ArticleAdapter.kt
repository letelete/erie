package io.erie.ui.global.article

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import io.erie.R
import io.erie.commons.extensions.inflate
import io.erie.commons.utils.ArticleDiffUtilCallback
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article

class ArticleAdapter
    : PagedListAdapter<ArticleEntity,
        ArticleViewHolder>(ArticleDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val rootView = parent.inflate(R.layout.item_article)
        return ArticleViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }
}