package io.erie.ui.topheadlines

import dagger.Module
import dagger.Provides
import io.erie.base.AppSchedulers
import io.erie.repository.ArticleRepository

@Module
class TopHeadlinesModule {

    @Provides
    fun provideTopHeadlinesPresenter(
        articleRepository: ArticleRepository,
        appSchedulers: AppSchedulers
    ): TopHeadlinesPresenter =
        TopHeadlinesPresenter(articleRepository, appSchedulers)
}