package io.erie.di.modules

import dagger.Module
import dagger.Provides
import io.erie.base.AppSchedulers
import io.erie.di.Qualifiers.Companion.EXECUTOR_SINGLE_THREAD
import io.erie.model.db.NewsApiDb
import io.erie.network.ApiService
import io.erie.network.ApiService.Companion.BASE_API_URL
import io.erie.repository.ArticleRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideAppSchedulers(): AppSchedulers = AppSchedulers()

    @Provides
    @Named(EXECUTOR_SINGLE_THREAD)
    fun provideSingleThreadExecutor(): ExecutorService = Executors.newSingleThreadExecutor()

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    fun provideArticleRepository(
        api: ApiService,
        db: NewsApiDb,
        @Named(EXECUTOR_SINGLE_THREAD) executor: ExecutorService
    ): ArticleRepository =
        ArticleRepository(
            service = api,
            database = db,
            executor = executor
        )
}