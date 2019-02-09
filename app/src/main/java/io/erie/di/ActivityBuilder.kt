package io.erie.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.erie.ui.feed.FeedActivity
import io.erie.ui.feed.FeedModule

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FeedModule::class])
    abstract fun bindFeedActivity(): FeedActivity
}