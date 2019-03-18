package io.erie.ui.allarticles

import android.os.Bundle
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import io.erie.R
import io.erie.base.BaseFragment
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article
import io.erie.ui.global.article.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_allarticles.*
import javax.inject.Inject

class AllArticlesFragment : BaseFragment<AllArticlesPresenter>(),
    AllArticlesView, AllArticlesFilterDialogFragment.OnClickListener {

    @Inject lateinit var articleAdapter: ArticleAdapter

    override fun getLayout(): Int = R.layout.fragment_allarticles

    override fun initialize() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_allarticles.layoutManager = LinearLayoutManager(context)
        recyclerview_allarticles.adapter = articleAdapter
        recyclerview_allarticles.setHasFixedSize(true)

        presenter.fetchArticles()
    }

    override fun updateListContent(articles: PagedList<ArticleEntity>) {
        articleAdapter.submitList(articles)
    }

    override fun onLanguageClick(languagesChip: Chip) {
    }

    override fun onDateFromClick(dateFromChip: Chip) {
    }

    override fun onDateToClick(dateToChip: Chip) {
    }
}