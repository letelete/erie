package io.erie.ui.erie

import dagger.Module
import dagger.Provides
import io.erie.commons.PreferencesManager
import io.erie.ui.allarticles.AllArticlesFilterDialogFragment
import io.erie.ui.allarticles.AllArticlesFragment
import io.erie.ui.erie.adapters.ViewPagerAdapter
import io.erie.ui.topheadlines.TopHeadlinesFilterDialogFragment
import io.erie.ui.topheadlines.TopHeadlinesFragment

@Module
open class ErieModule {

    @Provides
    fun providesViewPagerAdapter(activity: ErieActivity): ViewPagerAdapter =
        ViewPagerAdapter(fragmentManager = activity.supportFragmentManager)

    @Provides
    fun providesEriePresenter(preferencesManager: PreferencesManager): EriePresenter =
        EriePresenter(preferencesManager)

    @Provides
    fun providesTopHeadlinesFragment(): TopHeadlinesFragment = TopHeadlinesFragment()

    @Provides
    fun providesAllArticlesFragment(): AllArticlesFragment = AllArticlesFragment()

    @Provides
    fun providesTopHeadlinesBottomSheetDialogFragment(): TopHeadlinesFilterDialogFragment =
        TopHeadlinesFilterDialogFragment()

    @Provides
    fun providesAllArticlesBottomSheetDialogFragment(): AllArticlesFilterDialogFragment =
        AllArticlesFilterDialogFragment()
}