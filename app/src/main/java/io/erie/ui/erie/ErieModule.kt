package io.erie.ui.erie

import dagger.Module
import dagger.Provides
import io.erie.ui.erie.adapters.ViewPagerAdapter

@Module
class ErieModule {

    @Provides
    fun providesViewPagerAdapter(activity: ErieActivity): ViewPagerAdapter =
        ViewPagerAdapter(fragmentManager = activity.supportFragmentManager)

    @Provides
    fun providesEriePresenter(): EriePresenter = EriePresenter()
}