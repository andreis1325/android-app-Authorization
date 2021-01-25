package com.example.authorization.net.services

import com.example.authorization.net.responses.ArticleResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ReportService {

    @GET("/api/v2/reports")
    fun getReports(): Observable<ArrayList<ArticleResponse>>

    @GET("/api/v2/reports/{id}")
    fun getReportById(
        @Path("id") id: String
    ): Observable<ArticleResponse>
}