package io.erie.ui.allarticles

import android.util.Log
import io.erie.base.BasePresenter
import io.erie.base.BaseSchedulers
import io.erie.repository.ArticleRepository

class AllArticlesPresenter(
    private val repository: ArticleRepository,
    private val schedulers: BaseSchedulers
) :
    BasePresenter<AllArticlesView>() {
    fun fetchArticles() {
        compositeObservable.add(
            repository.allArticles().pagedList
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe(
                    { articles -> getView()?.updateListContent(articles) },
                    { error -> Log.e(javaClass.name, error.message) }
                )
        )
    }
}