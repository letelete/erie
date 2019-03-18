package io.erie.ui.allarticles

import dagger.Module
import dagger.Provides
import io.erie.base.AppSchedulers
import io.erie.repository.ArticleRepository

@Module
class AllArticlesModule {
    @Provides
    fun provideAllArticlesPresenter(
        repository: ArticleRepository,
        schedulers: AppSchedulers
    ): AllArticlesPresenter = AllArticlesPresenter(repository, schedulers)
}