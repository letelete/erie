package io.erie.ui.topheadlines

import android.os.Bundle
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import io.erie.R
import io.erie.base.BaseFragment
import io.erie.model.entities.ArticleEntity
import io.erie.ui.global.article.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_topheadlines.*
import javax.inject.Inject

class TopHeadlinesFragment : BaseFragment<TopHeadlinesPresenter>(), TopHeadlinesView,
    TopHeadlinesFilterDialogFragment.OnClickListener {

    @Inject lateinit var articleAdapter: ArticleAdapter

    override fun getLayout(): Int = R.layout.fragment_topheadlines

    override fun initialize() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_topheadlines.layoutManager = LinearLayoutManager(context)
        recyclerview_topheadlines.adapter = articleAdapter
        recyclerview_topheadlines.setHasFixedSize(true)

        presenter.fetchArticles()
    }

    override fun updateListContent(articles: PagedList<ArticleEntity>) {
        articleAdapter.submitList(articles)
    }

    override fun onCountryClick(languagesChip: Chip) {
    }

    override fun onCategoryClick(dateFromChip: Chip) {
    }
}