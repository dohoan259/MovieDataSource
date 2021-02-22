package com.example.moviedatasource.data.remote.service

import com.example.moviedatasource.data.remote.model.response.*
import com.example.moviedatasource.data.remote.model.response.MovieCreditsResponse
import com.example.moviedatasource.data.remote.model.response.MovieResponse
import com.example.moviedatasource.data.remote.model.response.MovieStatesResponse
import com.example.moviedatasource.data.remote.model.response.MovieVideosResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): NetworkResponse<MovieResponse, ErrorResponse>

    @GET("movie/{movieId}/account_states")
    fun getAccountStatesForMovie(@Path("movieId") movieId: Int): Deferred<NetworkResponse<MovieStatesResponse, ErrorResponse>>

    @GET("movie/{movieId}/videos")
    fun getVideosForMovie(@Path("movieId") movieId: Int): Deferred<NetworkResponse<MovieVideosResponse, ErrorResponse>>

    @GET("movie/{movieId}/credits")
    fun getCreditsForMovie(@Path("movieId") movieId: Int): Deferred<NetworkResponse<MovieCreditsResponse, ErrorResponse>>

    @GET("movie/{movieId}/similar")
    fun getSimilarMoviesForMovie(@Path("movieId") movieId: Int): Deferred<NetworkResponse<SimilarMoviesResponse, ErrorResponse>>
}