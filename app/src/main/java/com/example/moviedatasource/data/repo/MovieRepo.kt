package com.example.moviedatasource.data.repo

import android.util.Log
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.CollectionType
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieCollection
import com.example.moviedatasource.data.model.MovieDetail
import com.example.moviedatasource.data.remote.source.NetworkBoundResource
import com.example.moviedatasource.data.remote.source.RemoteCollectionSource
import com.example.moviedatasource.data.remote.source.RemoteMovieSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val localMovieSource: LocalMovieSource,
    private val remoteMovieSource: RemoteMovieSource,
    private val remoteCollectionSource: RemoteCollectionSource
) {
    @FlowPreview
    @ExperimentalCoroutinesApi
    suspend fun getMovieDetails(movieId: Int) =
        object : NetworkBoundResource<MovieDetail, Movie>() {
            override suspend fun fetchFromNetwork(): Resource<Movie> {
                return remoteMovieSource.getMovieDetails(movieId)
            }

            override suspend fun saveNetworkResult(item: Movie) {
                localMovieSource.saveMovie(item)
            }

            override suspend fun shouldFetch(resultType: MovieDetail?): Boolean {
                return resultType == null || !resultType.movie.isModelComplete
            }

            override suspend fun loadFromDb(): Flow<MovieDetail> {
                return localMovieSource.getMovieDetails(movieId = movieId)
            }
        }

//    suspend fun getActorsInMovie(movieId: Int): Flow<Resource<List<Actor>>> {
//        return localMovieSource.getActorsInMovie(movieId)
//            .map { actors -> Resource.Success(actors) }
//    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getCollection(type: CollectionType, region: String = "") =
        object : NetworkBoundResource<List<Movie>, List<Movie>>() {
            override suspend fun fetchFromNetwork(): Resource<List<Movie>> {
                return remoteCollectionSource.getCollection(type = type, region = region)
            }

            override suspend fun saveNetworkResult(item: List<Movie>) {
                localMovieSource.saveMoviesForCollection(
                    MovieCollection(type.name),
                    item
                )
            }

            override suspend fun shouldFetch(resultType: List<Movie>?): Boolean {
                return resultType == null || resultType.isEmpty()
            }

            override suspend fun loadFromDb(): Flow<List<Movie>> {
                return localMovieSource.getCollectionWithMovie(type = type)
                    .map { collectionWithMovies ->

                        Log.d("hoan.dv", "aa: $collectionWithMovies")

                        if (collectionWithMovies?.movies.isNullOrEmpty()) {
                            ArrayList()
                        } else {
                            collectionWithMovies?.movies!!
                        }
                    }
            }
        }.asFlow()

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getSearchResultForQuery(query: String) =
        object : NetworkBoundResource<List<Movie>, List<Movie>>() {
            override suspend fun fetchFromNetwork(): Resource<List<Movie>> {
                return remoteMovieSource.getSearchResultForQuery(query)
            }

            override suspend fun saveNetworkResult(item: List<Movie>) {

            }

            override suspend fun shouldFetch(resultType: List<Movie>?): Boolean {
                return true
            }

            override suspend fun loadFromDb(): Flow<List<Movie>> {
                return flow {}
            }
        }.asFlow()
}