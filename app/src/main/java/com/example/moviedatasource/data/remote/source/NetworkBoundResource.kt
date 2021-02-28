package com.example.moviedatasource.data.remote.source

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.moviedatasource.data.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
abstract class NetworkBoundResource<ResultType, RequestType> {
    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val dbResult = loadFromDb().flatMapConcat { dbResource ->
            if (shouldFetch(dbResource)) {
                val remoteResource = fetchFromNetwork()
                if (remoteResource is Resource.Success) {
                    remoteResource.data?.let {
                        saveNetworkResult(it)
                    }
                    loadFromDb().map {
                        Resource.Success(it)
                    }
                } else {
                    onFetchFailed()
                    loadFromDb().map {
                        Resource.Error(remoteResource.message!!, it)
                    }
                }
            } else {
                loadFromDb().map {
                    Resource.Success(it)
                }
            }
        }
        emitAll(dbResult)
    }

    protected open fun onFetchFailed() {
        // Implement in sub-classes to handle errors
    }

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(item: RequestType)

    @MainThread
    protected abstract suspend fun shouldFetch(resultType: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): Flow<ResultType>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Resource<RequestType>
}