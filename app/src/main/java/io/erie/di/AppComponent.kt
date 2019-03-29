package io.erie.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.erie.ErieApp
import io.erie.di.modules.DatabaseModule
import io.erie.di.modules.NetworkModule
import io.erie.di.modules.PreferencesModule
import io.erie.di.modules.UiModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        PreferencesModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        UiModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<ErieApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ErieApp>()
}