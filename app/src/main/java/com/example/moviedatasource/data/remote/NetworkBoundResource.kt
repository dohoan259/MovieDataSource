package com.example.moviedatasource.data.remote

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.moviedatasource.data.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
abstract class NetworkBoundResource<T> {
    fun asFlow(): Flow<Resource<T>> = flow<Resource<T>> {
        emit(Resource.Loading())
        shouldFetch().collect { shouldFetch ->

            Log.d("hoan.dv", "shouldFetch: $shouldFetch")

            if (shouldFetch) {
//                loadFromDb().collect { dbResource ->
//                    Log.d("hoan.dv", "emit loading from db: ${dbResource.data}")
//                    emit(Resource.Loading(data = dbResource.data))
//                }

//                fetchFromNetwork().map { remoteResource ->
//
//                    Log.d("hoan.dv", "map remoteResource: $remoteResource")
//
//                    if (remoteResource is Resource.Success) {
//                        remoteResource.data?.let { saveNetworkResult(it) }
//                    }
//                }

                fetchFromNetwork().collect { remoteResource ->

                    Log.d("hoan.dv", "remoteResource: $remoteResource")

                    if (remoteResource is Resource.Success) {
                        remoteResource.data?.let { saveNetworkResult(it) }
                        loadFromDb().collect { dbResource ->
                            dbResource.data?.let {
                                emit(Resource.Success(it))
                            }
                        }
                    } else {
                        onFetchFailed()
                        loadFromDb().collect { dbResource ->
                            dbResource.data?.let {
                                emit(Resource.Success(it))
                            }
                        }
                    }
                }
            } else {
                loadFromDb().collect { dbResource ->
                    dbResource.data?.let {
                        emit(Resource.Success(it))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {
        // Implement in sub-classes to handle errors
    }

    @WorkerThread
    protected abstract suspend fun saveNetworkResult(item: T)

    @MainThread
    protected abstract suspend fun shouldFetch(): Flow<Boolean>

    @MainThread
    protected abstract suspend fun loadFromDb(): Flow<Resource<T>>

    @MainThread
    protected abstract suspend fun fetchFromNetwork(): Flow<Resource<T>>
}