package com.example.moviedatasource.data.remote.source

import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.CollectionType
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.remote.service.discovery.DiscoveryService
import com.example.moviedatasource.utils.toMovie
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCollectionSource @Inject constructor(
    private val discoveryService: DiscoveryService
) {

    suspend fun getCollection(type: CollectionType, region: String = ""): Resource<List<Movie>> {
        return when (type) {
            CollectionType.Popular -> getPopularMovies()
            CollectionType.TopRated -> getTopRatedMovies()
            CollectionType.InTheatres -> getInTheatresMovies(region)
            else -> getPopularMovies()
        }
    }

    private suspend fun getPopularMovies(): Resource<List<Movie>> {
        return when (val response = discoveryService.getPopularMovies()) {
            is NetworkResponse.Success -> {
                Resource.Success(
                    response.body.results.map { generalMovie ->
                        generalMovie.toMovie()
                    }
                )
            }
            is NetworkResponse.ServerError -> {
                Resource.Error(response.body?.statusMessage ?: "Server Error")
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(response.error.localizedMessage ?: "Network Error")
            }
            is NetworkResponse.UnknownError -> {
                Resource.Error(response.error.localizedMessage ?: "Unknown Error")
            }
        }
    }

    private suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return when (val response = discoveryService.getTopRatedMovies()) {
            is NetworkResponse.Success -> {
                Resource.Success(
                    response.body.results.map { generalMovie ->
                        generalMovie.toMovie()
                    }
                )
            }
            is NetworkResponse.ServerError -> {
                Resource.Error(response.body?.statusMessage ?: "Server Error")
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(response.error.localizedMessage ?: "Network Error")
            }
            is NetworkResponse.UnknownError -> {
                Resource.Error(response.error.localizedMessage ?: "Unknown Error")
            }
        }
    }

    private suspend fun getInTheatresMovies(region: String): Resource<List<Movie>> {
        return when (val response = discoveryService.getMoviesInTheatre(region = region)) {
            is NetworkResponse.Success -> {
                Resource.Success(
                    response.body.results.map { generalMovie ->
                        generalMovie.toMovie()
                    }
                )
            }
            is NetworkResponse.ServerError -> {
                Resource.Error(response.body?.statusMessage ?: "Server Error")
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(response.error.localizedMessage ?: "Network Error")
            }
            is NetworkResponse.UnknownError -> {
                Resource.Error(response.error.localizedMessage ?: "Unknown Error")
            }
        }
    }
}