package io.erie.ui.topheadlines

import android.util.Log
import io.erie.base.BasePresenter
import io.erie.base.BaseSchedulers
import io.erie.repository.ArticleRepository

class TopHeadlinesPresenter(
    private val repository: ArticleRepository,
    private val schedulers: BaseSchedulers
) :
    BasePresenter<TopHeadlinesView>() {

    fun fetchArticles() {
        compositeObservable.add(
            repository.topHeadlinesArticles().pagedList
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                    { articles -> getView()?.updateListContent(articles) },
                    { error -> Log.e(javaClass.name, error.message) }
                )
        )
    }
}