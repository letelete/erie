package io.erie.network

import io.erie.model.Comment
import io.erie.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts")
    fun posts(): Observable<List<Post>>

    @GET("/posts/{post_id}/comments")
    fun postComments(
        @Path("post_id") postId: Int
    ): Observable<List<Comment>>
}