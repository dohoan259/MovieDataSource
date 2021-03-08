package com.example.moviedatasource.data.remote.service.discovery

import com.example.moviedatasource.data.remote.model.response.DiscoverMoviesResponse
import com.example.moviedatasource.data.remote.model.response.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

private val df = SimpleDateFormat("yyyy-MM-dd", Locale.US)
private val currentDate: String = df.format(Date())
private val pastDate = run {
    val day = Calendar.getInstance().apply { add(Calendar.MONTH, -1) }
    df.format(Date(day.timeInMillis))
}

interface DiscoveryService {

    @GET("discover/movie")
    suspend fun getMoviesInTheatre(
        @Query("sort_by") sortBy: String = SortParameters.PopularityDsc,
        @Query("page") page: Int = 1,
        @Query("region") region: String,
        @Query("primary_release_date.lte") releaseDateGte: String = currentDate,
        @Query("primary_release_date.gte") releaseDateLte: String = pastDate
    ): NetworkResponse<DiscoverMoviesResponse, ErrorResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): NetworkResponse<DiscoverMoviesResponse, ErrorResponse>

//    @GET("movie/popular")
//    suspend fun getPopularMovies(): ApiResponse<DiscoverMoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): NetworkResponse<DiscoverMoviesResponse, ErrorResponse>

}