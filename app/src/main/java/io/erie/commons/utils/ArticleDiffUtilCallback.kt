package io.erie.commons.utils

import androidx.recyclerview.widget.DiffUtil
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article

class ArticleDiffUtilCallback : DiffUtil.ItemCallback<ArticleEntity>() {
    override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean =
        oldItem.title == newItem.title
}