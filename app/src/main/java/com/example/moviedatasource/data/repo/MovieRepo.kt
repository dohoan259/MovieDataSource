package com.example.moviedatasource.data.repo

import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.model.Actor
import com.example.moviedatasource.data.model.CollectionType
import com.example.moviedatasource.data.model.Movie
import com.example.moviedatasource.data.remote.NetworkBoundResource
import com.example.moviedatasource.data.remote.RemoteMovieSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val localMovieSource: LocalMovieSource,
    private val remoteMovieSource: RemoteMovieSource,
) {
    @FlowPreview
    @ExperimentalCoroutinesApi
    suspend fun getMovieDetails(movieId: Int) =
        object : NetworkBoundResource<Movie>() {
            override suspend fun fetchFromNetwork(): Flow<Resource<Movie>> {
                return remoteMovieSource.getMovieDetails(movieId)
            }

            override suspend fun saveNetworkResult(item: Movie) {
                localMovieSource.saveMovie(item)
            }

            override suspend fun shouldFetch(): Flow<Boolean> {
                val isInDb = localMovieSource.isMovieInDatabase(movieId)
                    .map { count ->
                        count == 0
                    }
                val isModelComplete = localMovieSource
                    .getMovieDetails(movieId)
                    .map { movie ->
                        movie.isModelComplete
                    }

                return isInDb.zip(isModelComplete) { dbStatus, modelStatus ->
                    !(dbStatus && modelStatus)
                }
            }

            override suspend fun loadFromDb(): Flow<Resource<Movie>> {
                return localMovieSource.getMovieDetails(movieId = movieId).map { movie ->
                    Resource.Success(movie)
                }
            }
        }

    suspend fun getActorsInMovie(movieId: Int): Flow<Resource<List<Actor>>> {
        return localMovieSource.getActorsInMovie(movieId)
            .map { actors -> Resource.Success(actors) }
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getPopularMovies() =
        object : NetworkBoundResource<List<Movie>>() {
            override suspend fun fetchFromNetwork(): Flow<Resource<List<Movie>>> {
                return remoteMovieSource.getPopularMovies()
            }

            override suspend fun saveNetworkResult(item: List<Movie>) {
                localMovieSource.saveMovies(item)
            }

            override suspend fun shouldFetch(): Flow<Boolean> {
                return localMovieSource.isCollectionInDatabase(CollectionType.Popular)
                    .map { it == 0 }
            }

            override suspend fun loadFromDb(): Flow<Resource<List<Movie>>> {
                return localMovieSource.getCollection(type = CollectionType.Popular)
                    .map { movieCollection ->
                        Resource.Success(movieCollection)
                    }
            }
        }.asFlow()
}