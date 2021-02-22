package com.example.moviedatasource.data.remote.service

import com.example.moviedatasource.data.remote.model.response.ErrorResponse
import com.example.moviedatasource.data.remote.model.response.PersonResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PersonService {

    @GET("person/{personId}")
    fun getPerson(@Path("personId") id: Int): Deferred<NetworkResponse<PersonResponse, ErrorResponse>>

}