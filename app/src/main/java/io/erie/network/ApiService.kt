package io.erie.network

import io.erie.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    fun posts(): Observable<List<Post>>

}