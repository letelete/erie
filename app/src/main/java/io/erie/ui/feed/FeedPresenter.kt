package io.erie.ui.feed

import io.erie.base.BasePresenter
import io.erie.base.BaseSchedulers
import io.erie.network.ApiService
import javax.inject.Inject

class FeedPresenter @Inject constructor(
    private val schedulers: BaseSchedulers,
    private val apiService: ApiService
) : BasePresenter<FeedContract.View>(),
    FeedContract.Presenter {

    override fun initialize() {
        getView()?.initializeView()
    }

    override fun fetchPosts() {
        compositeObservable.add(
            apiService.posts()
                .doOnSubscribe { getView()?.showLoading() }
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
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