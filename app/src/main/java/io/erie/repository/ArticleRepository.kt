package io.erie.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.erie.model.db.NewsApiDb
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.Article
import io.erie.network.ApiService
import io.erie.network.Sources
import io.erie.repository.boundarycallbacks.AllArticlesBoundaryCallback
import io.erie.repository.boundarycallbacks.TopHeadlinesBoundaryCallback
import io.erie.repository.mapper.ArticleMapper
import java.util.concurrent.Executor

class ArticleRepository(
    private val service: ApiService,
    private val database: NewsApiDb,
    private val executor: Executor,
    private val pageSize: Int = DEFAULT_PAGE_SIZE
) {
    companion object {
        private const val INITIAL_LOAD_SIZE = 5
        private const val DEFAULT_PAGE_SIZE = 16
        private val DEFAULT_NEWS_SOURCES = Sources(
            listOf(
                "the-new-york-times",
                "the-wall-street-journal",
                "the-washington-post",
                "abc-news",
                "bbc-news",
                "bbc-sport",
                "wired",
                "politico",
                "usa-today",
                "cnn",
                "nbc-news",
                "fortune",
                "bloomberg",
                "daily-mail",
                "fox-news",
                "time",
                "hacker-news",
                "reddit-r-all",
                "techcrunch",
                "techradar"
            )
        )
    }

    private val pagedListConfig by lazy {
        PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
            .setPrefetchDistance(pageSize / 2)
            .build()
    }

    fun topHeadlinesArticles(
        sources: Sources = DEFAULT_NEWS_SOURCES
    ): Listing<ArticleEntity> {
        val boundaryCallback = TopHeadlinesBoundaryCallback(
            sources = sources,
            service = service,
            handleResponse = ::insertTopHeadlines,
            ioExecutor = executor,
            pageSize = pageSize
        )

        val dataFactory = database.articleDao().topHeadlinesArticles()
        val pagedList = RxPagedListBuilder(dataFactory, pagedListConfig)
            .setBoundaryCallback(boundaryCallback)
            .buildObservable()

        return Listing(pagedList)
    }

    private fun insertTopHeadlines(articles: List<Article>) {
        val entities = articles
            .filter { it.hasEssentialAttributes() }
            .map {
                ArticleMapper.mapFromResponse(it)
                    .also { mappedEntity -> mappedEntity.fromTopHeadlines = true }
            }
        insertArticlesIntoDatabase(entities)
    }

    fun allArticles(
        sources: Sources = DEFAULT_NEWS_SOURCES,
        fromDate: String? = null,
        toDate: String? = null
    ): Listing<ArticleEntity> {
        val boundaryCallback = AllArticlesBoundaryCallback(
            sources = sources,
            fromDate = fromDate,
            toDate = toDate,
            service = service,
            handleResponse = ::insertAllArticles,
            ioExecutor = executor,
            pageSize = pageSize
        )

        val dataFactory = database.articleDao().allArticles()
        val pagedList = RxPagedListBuilder(dataFactory, pagedListConfig)
            .setBoundaryCallback(boundaryCallback)
            .buildObservable()

        return Listing(pagedList)
    }

    private fun insertAllArticles(articles: List<Article>) {
        val entities = articles
            .filter { it.hasEssentialAttributes() }
            .map {
                ArticleMapper.mapFromResponse(it)
                    .also { mappedEntity -> mappedEntity.fromAllArticles = true }
            }
        insertArticlesIntoDatabase(entities)
    }

    private fun insertArticlesIntoDatabase(articles: List<ArticleEntity>) {
        executor.execute {
            database.articleDao().insertArticles(articles)
        }
    }
}