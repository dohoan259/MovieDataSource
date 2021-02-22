package com.example.moviedatasource.data.remote.service

import com.example.moviedatasource.data.remote.model.response.ErrorResponse
import com.example.moviedatasource.data.remote.model.response.SearchResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/movie")
    fun searchForMovie(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Deferred<NetworkResponse<SearchResponse, ErrorResponse>>
}