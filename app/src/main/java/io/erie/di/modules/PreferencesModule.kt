package io.erie.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.erie.commons.PreferencesManager
import io.erie.commons.PreferencesManagerImpl

@Module
class PreferencesModule {
    @Provides
    fun providePreferencesManager(context: Context): PreferencesManager =
        PreferencesManagerImpl(context)
}