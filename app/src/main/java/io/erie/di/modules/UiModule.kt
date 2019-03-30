package io.erie.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.erie.commons.utils.HumanDate
import io.erie.ui.global.article.ArticleAdapter

@Module
class UiModule {
    @Provides
    fun humanDate(context: Context): HumanDate =
        HumanDate(context)

    @Provides
    fun provideArticleAdapter(humanDate: HumanDate): ArticleAdapter =
        ArticleAdapter(humanDate)
}