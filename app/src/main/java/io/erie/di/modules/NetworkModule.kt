package io.erie.di.modules

import dagger.Module
import dagger.Provides
import io.erie.base.AppSchedulers
import io.erie.commons.Constants.Companion.BASE_URL
import io.erie.network.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideSchedulers(): AppSchedulers = AppSchedulers()

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}