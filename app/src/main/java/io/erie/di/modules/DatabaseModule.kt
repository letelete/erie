package io.erie.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.erie.model.db.NewsApiDb
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideNewsApiDatabase(context: Context): NewsApiDb = NewsApiDb.create(context)
}