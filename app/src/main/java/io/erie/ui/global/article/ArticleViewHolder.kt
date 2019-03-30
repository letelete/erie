package io.erie.ui.global.article

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.erie.commons.utils.HumanDate
import io.erie.model.entities.ArticleEntity
import kotlinx.android.synthetic.main.include_all_author.view.*
import kotlinx.android.synthetic.main.item_article.view.*
import javax.inject.Inject

class ArticleViewHolder(private val view: View, private val humanDate: HumanDate) :
    RecyclerView.ViewHolder(view) {

    @Inject lateinit var context: Context
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

    private fun formatArticleDetails() =
        "${humanDate.timePeriod(article.publishedAt)} • ${article.readTime}"
}