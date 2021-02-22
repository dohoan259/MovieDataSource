package com.example.moviedatasource.data.remote.service

import com.example.moviedatasource.data.remote.model.request.ToggleMediaFavouriteStatusRequest
import com.example.moviedatasource.data.remote.model.request.ToggleMediaWatchlistStatusRequest
import com.example.moviedatasource.data.remote.model.response.*
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {

    @GET("account")
    fun getAccountDetails(): Deferred<NetworkResponse<AccountDetailsResponse, ErrorResponse>>

    @GET("account/{accountId}/watchlist/movies")
    fun getMoviesWatchList(@Path("accountId") accountId: Int): Deferred<NetworkResponse<MovieWatchlistResponse, ErrorResponse>>

    @GET("account/{accountId}/favorite/movies")
    fun getFavouriteMovies(@Path("accountId") accountId: Int): Deferred<NetworkResponse<FavouriteMoviesResponse, ErrorResponse>>

    @POST("account/{accountId}/favorite")
    fun toggleMediaFavouriteStatus(
        @Path("accountId") accountId: Int,
        @Body request: ToggleMediaFavouriteStatusRequest
    ): Flow<ToggleFavouriteResponse>

    @POST("account/{accountId}/watchlist")
    fun toggleMediaWatchlistStatus(
        @Path("accountId") accountId: Int,
        @Body request: ToggleMediaWatchlistStatusRequest
    ): Flow<ToggleWatchlistResponse>
}
