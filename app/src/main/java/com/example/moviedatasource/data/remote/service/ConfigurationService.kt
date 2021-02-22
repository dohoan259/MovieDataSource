package com.example.moviedatasource.data.remote.service

import com.example.moviedatasource.data.remote.model.response.ConfigurationResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

internal interface ConfigurationService {

    @GET("configuration")
    fun getConfiguration(): Flow<ConfigurationResponse>

}