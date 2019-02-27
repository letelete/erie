package io.erie.ui.erie

import dagger.Module
import dagger.Provides
import io.erie.commons.PreferencesManager
import io.erie.ui.erie.adapters.ViewPagerAdapter

@Module
open class ErieModule {

    @Provides
    fun providesViewPagerAdapter(activity: ErieActivity): ViewPagerAdapter =
        ViewPagerAdapter(fragmentManager = activity.supportFragmentManager)

    @Provides
    fun providesEriePresenter(preferencesManager: PreferencesManager): EriePresenter =
        EriePresenter(preferencesManager)
}