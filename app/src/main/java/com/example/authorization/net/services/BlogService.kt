package com.example.authorization.net.services

import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.net.responses.BlogResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogService{

    @GET("/api/v2/blogs")
    fun getBlogs(): Observable<List<BlogResponse>>

    @GET("/api/v2/blogs/{id}")
    fun getBlogById(
        @Path("id") id: String
    ): Observable<BlogResponse>

}
