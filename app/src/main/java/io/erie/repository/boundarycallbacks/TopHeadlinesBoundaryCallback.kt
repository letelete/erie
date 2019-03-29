package io.erie.repository.boundarycallbacks

import io.erie.base.BaseBoundaryCallback
import io.erie.model.responses.Article
import io.erie.model.responses.ArticlesContainer
import io.erie.network.ApiService
import io.erie.network.Sources
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class TopHeadlinesBoundaryCallback(
    private val sources: Sources,
    private val service: ApiService,
    private val handleResponse: (List<Article>) -> Unit,
    private val ioExecutor: Executor,
    private val pageSize: Int
) : BaseBoundaryCallback() {

    override fun getExecutor(): Executor = ioExecutor

    override fun onCallbackFailure() {
    }

    override fun onCallbackResponse(response: Response<ArticlesContainer>) {
        val articles = response.body()?.articles
        articles?.let {
            handleResponse(articles)
        }
    }

    override fun getArticles(page: Int): Call<ArticlesContainer> =
        service.getTopHeadlines(
            pageSize = pageSize,
            page = page,
            sources = sources
        )
}