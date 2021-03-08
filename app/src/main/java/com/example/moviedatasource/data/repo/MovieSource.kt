package com.example.moviedatasource.data.repo

import com.example.moviedatasource.data.local.entity.Cast
import com.example.moviedatasource.data.local.entity.CollectionType
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieCollection
import com.example.moviedatasource.data.model.*
import kotlinx.coroutines.flow.Flow

interface MovieSource {

    suspend fun getAllMovies(): Flow<List<Movie>>

    fun getMovieDetails(movieId: Int): Flow<MovieDetail>

    suspend fun saveMovie(movie: Movie)

    suspend fun saveMovies(movies: List<Movie>)

    suspend fun saveMoviesForCollection(collection: MovieCollection, movies: List<Movie>)

//    suspend fun getActorsInMovie(movieId: Int): Flow<List<Actor>>

    suspend fun saveCast(cast: Cast)

    suspend fun isMovieInDatabase(id: Int): Flow<Int>

    suspend fun isCollectionInDatabase(type: CollectionType): Flow<Int>

    fun getCollectionWithMovie(type: CollectionType): Flow<CollectionWithMovies?>
}