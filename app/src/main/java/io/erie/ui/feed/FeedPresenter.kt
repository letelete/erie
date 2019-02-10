package io.erie.ui.feed

import io.erie.base.AppSchedulers
import io.erie.base.BasePresenter
import io.erie.network.ApiService
import javax.inject.Inject

class FeedPresenter @Inject constructor(
    private val schedulers: AppSchedulers,
    private val apiService: ApiService
) : BasePresenter<FeedContract.View>(),
    FeedContract.Presenter {

    override fun initialize() {
        getView()?.initializeView()
    }

    override fun fetchPosts() {
        getView()?.showLoading()
        compositeObservable.add(
            apiService.posts()
                .observeOn(schedulers.mainTread())
                .subscribeOn(schedulers.backgroundThread())
                .doOnTerminate { getView()?.hideLoading() }
                .subscribe(
                    { getView()?.showPostsList(it) },
                    { getView()?.showPostsFetchingError(it.message.toString()) })
        )
    }

    override fun detachView() {
        getView()?.hideLoading()
        super.detachView()
    }
}