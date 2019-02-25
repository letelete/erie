package io.erie.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.erie.ErieApp
import io.erie.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<ErieApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ErieApp>()
}