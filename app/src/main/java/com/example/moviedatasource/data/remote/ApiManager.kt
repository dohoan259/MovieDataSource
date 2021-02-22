package com.example.moviedatasource.data.remote

import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.remote.model.request.CreateSessionRequest
import com.example.moviedatasource.data.remote.model.response.AccountDetailsResponse
import com.example.moviedatasource.data.remote.service.AccountService
import com.example.moviedatasource.data.remote.service.AuthenticationService
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiManager internal constructor(
    private val authenticationService: AuthenticationService,
    private val accountService: AccountService
) {

    fun getRequestToken(): Flow<Resource<String>>
    = flow {
        when (val response = authenticationService.getRequestToken().await()) {
            is NetworkResponse.Success -> {
                emit(Resource.Success(response.body.requestToken))
            }
            is NetworkResponse.ServerError -> {
                emit(Resource.Error<String>(errorMessage = response.body?.statusMessage ?: "Server error"))
            }
            is NetworkResponse.NetworkError -> {
                emit(Resource.Error<String>(response.error.localizedMessage ?: "Network Error"))
            }

        }
    }

    fun createSession(request: CreateSessionRequest): Flow<Resource<String>>
    = flow {

        when (val response = authenticationService.createNewSession(request).await()) {
                    is NetworkResponse.Success -> {
                        emit(Resource.Success(response.body.sessionId))
                    }
                    is NetworkResponse.ServerError -> {
                        emit(Resource.Error<String>(errorMessage = response.body?.statusMessage ?: "Server error"))
                    }
                    is NetworkResponse.NetworkError -> {
                        emit(Resource.Error<String>(response.error.localizedMessage ?: "Network Error"))
                    }
                }
    }

    fun getAccountDetails(): Flow<Resource<AccountDetailsResponse>>
    = flow{

        when(val response = accountService.getAccountDetails().await()) {
                    is NetworkResponse.Success -> {
                        emit(Resource.Success(response.body))
                    }
                    is NetworkResponse.ServerError -> {
                        emit(Resource.Error<AccountDetailsResponse>(errorMessage = response.body?.statusMessage ?: "Server error"))
                    }
                    is NetworkResponse.NetworkError -> {
                        emit(Resource.Error<AccountDetailsResponse>(response.error.localizedMessage ?: "Network Error"))
                    }
                }
    }

}