package io.erie.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.erie.ui.allarticles.AllArticlesFragment
import io.erie.ui.allarticles.AllArticlesModule
import io.erie.ui.erie.ErieActivity
import io.erie.ui.erie.ErieModule
import io.erie.ui.topheadlines.TopHeadlinesFragment
import io.erie.ui.topheadlines.TopHeadlinesModule

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ErieModule::class])
    abstract fun bindErieActivity(): ErieActivity

    @ContributesAndroidInjector(modules = [TopHeadlinesModule::class])
    abstract fun bindTopHeadlinesFragment(): TopHeadlinesFragment

    @ContributesAndroidInjector(modules = [AllArticlesModule::class])
    abstract fun bindAllArticlesFragment(): AllArticlesFragment
}