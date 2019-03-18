package io.erie.network

import io.erie.BuildConfig.API_KEY
import io.erie.model.responses.ArticlesContainer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_API_URL = "https://newsapi.org/v2/"
    }

    /**
     * Example of the API call to show top headlines from Techcrunch, Techradar and BBC News
     * https://newsapi.org/v2/top-headlines?sources=techcrunch,techradar,bbc-news&apiKey=API_KEY
     */

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("sources") sources: Sources
    ): Call<ArticlesContainer>

    @GET("everything")
    fun getAllArticles(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("sources") sources: Sources,
        @Query("from") fromDate: String? = null,
        @Query("to") toDate: String? = null
    ): Call<ArticlesContainer>
}