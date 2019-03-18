package io.erie.di.modules

import dagger.Module
import dagger.Provides
import io.erie.ui.global.article.ArticleAdapter

@Module
class UiModule {
    @Provides
    fun provideArticleAdapter(): ArticleAdapter =
        ArticleAdapter()
}