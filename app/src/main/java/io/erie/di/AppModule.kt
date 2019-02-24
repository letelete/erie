package io.erie.di

import android.content.Context
import dagger.Binds
import dagger.Module
import io.erie.ErieApp

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: ErieApp): Context
}