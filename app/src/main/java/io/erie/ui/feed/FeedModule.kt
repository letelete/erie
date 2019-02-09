package io.erie.ui.feed

import dagger.Module
import dagger.Provides
import io.erie.base.AppSchedulers
import io.erie.network.ApiService

@Module
open class FeedModule {
    @Provides
    open fun provideFeedPresenter(appSchedulers: AppSchedulers, apiService: ApiService) =
        FeedPresenter(appSchedulers, apiService)
}