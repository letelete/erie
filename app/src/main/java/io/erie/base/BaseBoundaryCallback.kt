package io.erie.base

import android.util.Log
import androidx.paging.PagedList
import io.erie.commons.utils.PagingRequestHelper
import io.erie.model.entities.ArticleEntity
import io.erie.model.responses.ArticlesContainer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

abstract class BaseBoundaryCallback :
    PagedList.BoundaryCallback<ArticleEntity>() {

    companion object {
        const val STARTING_PAGE = 1
    }

    abstract fun getExecutor(): Executor
    abstract fun getArticles(page: Int): Call<ArticlesContainer>
    abstract fun onCallbackResponse(response: Response<ArticlesContainer>)
    abstract fun onCallbackFailure()

    private val helper = PagingRequestHelper(this.getExecutor())
    private var pageCounter = STARTING_PAGE

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        pageCounter = STARTING_PAGE
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { callback ->
            getArticles(pageCounter)
                .enqueue(createWebserviceCallback(callback))
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: ArticleEntity) {
        super.onItemAtEndLoaded(itemAtEnd)
        pageCounter++
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { callback ->
            getArticles(pageCounter)
                .enqueue(createWebserviceCallback(callback))
        }
    }

    private fun createWebserviceCallback(callback: PagingRequestHelper.Request.Callback)
            : Callback<ArticlesContainer> = object : Callback<ArticlesContainer> {
        override fun onFailure(call: Call<ArticlesContainer>, t: Throwable) {
            onCallbackFailure()
            Log.e(javaClass.name, t.message)
            callback.recordFailure(t)
        }

        override fun onResponse(
            call: Call<ArticlesContainer>,
            response: Response<ArticlesContainer>
        ) {
            onCallbackResponse(response)
            callback.recordSuccess()
        }
    }
}