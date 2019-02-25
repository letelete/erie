package io.erie.ui.topheadlines

import dagger.Module
import dagger.Provides

@Module
class TopHeadlinesModule {
    @Provides
    fun provideTopHeadlinesPresenter(): TopHeadlinesPresenter = TopHeadlinesPresenter()
}