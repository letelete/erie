package io.erie.ui.global.article

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.erie.model.entities.ArticleEntity
import kotlinx.android.synthetic.main.include_all_author.view.*
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var article: ArticleEntity

    fun bind(article: ArticleEntity) {
        this.article = article
        val title = article.title
        val author = article.authorName
        val articleDetails = formatArticleDetails()
        val sourceName = article.sourceName
        view.textView_article_title.text = title
        view.include_article_author.textview_author_username.text = author
        view.include_article_author.textview_author_details.text = articleDetails
        view.include_article_author.chip_author_badge.text = sourceName
    }

    /* TODO: call to HumanDate class to get actual time period based on article.publishedAt */
    private fun formatArticleDetails() =
        "published time period • ${article.readTime}"
}