package io.erie.ui.allarticles

import dagger.Module
import dagger.Provides

@Module
class AllArticlesModule {
    @Provides
    fun provideAllArticlesPresenter(): AllArticlesPresenter = AllArticlesPresenter()
}