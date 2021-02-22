package com.example.moviedatasource.data.remote.service

import com.example.moviedatasource.data.remote.model.request.CreateSessionRequest
import com.example.moviedatasource.data.remote.model.request.DeleteSessionRequest
import com.example.moviedatasource.data.remote.model.response.CreateSessionResponse
import com.example.moviedatasource.data.remote.model.response.ErrorResponse
import com.example.moviedatasource.data.remote.model.response.GuestSessionResponse
import com.example.moviedatasource.data.remote.model.response.RequestTokenResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

internal interface AuthenticationService {

    @GET("authentication/guest_session/new")
    fun getGuestSessionToken(): Deferred<NetworkResponse<GuestSessionResponse, ErrorResponse>>

    @GET("authentication/token/new")
    fun getRequestToken(): Deferred<NetworkResponse<RequestTokenResponse, ErrorResponse>>

    @POST("authentication/session/new")
    fun createNewSession(@Body request: CreateSessionRequest): Deferred<NetworkResponse<CreateSessionResponse, ErrorResponse>>

    @DELETE("authentication/session")
    fun deleteSession(@Body request: DeleteSessionRequest): Deferred<NetworkResponse<DeleteSessionRequest, ErrorResponse>>
}