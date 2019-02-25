package io.erie

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.erie.di.DaggerAppComponent

class ErieApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)
}