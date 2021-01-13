package com.example.authorization.net.services

import com.example.authorization.net.responses.ArticleResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService{

    @GET("/api/v2/articles")
    fun getArticles(): Observable<ArticleResponse>

    @GET("/api/v2/articles/{id}")
    fun getArticlesById(
        @Path("id") id: String
    ): Observable<ArticleResponse>

    @GET("/api/v2/articles/launch/{id}")
    fun getArticlesLinkedByLaunch(
        @Path("id") id:String
    ):Observable<ArticleResponse>
}