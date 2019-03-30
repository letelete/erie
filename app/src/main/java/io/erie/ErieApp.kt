package io.erie

import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.erie.di.DaggerAppComponent

class ErieApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}