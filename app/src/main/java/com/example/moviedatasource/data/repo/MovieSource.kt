package com.example.moviedatasource.data.repo

import com.example.moviedatasource.data.model.Actor
import com.example.moviedatasource.data.model.Cast
import com.example.moviedatasource.data.model.CollectionType
import com.example.moviedatasource.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieSource {
    suspend fun getMovieDetails(movieId: Int): Flow<Movie>

    suspend fun saveMovie(movie: Movie)

    suspend fun saveMovies(movies: List<Movie>)

    suspend fun getActorsInMovie(movieId: Int): Flow<List<Actor>>

    suspend fun saveCast(cast: Cast)

    suspend fun isMovieInDatabase(id: Int): Flow<Int>

    suspend fun isCollectionInDatabase(type: CollectionType): Flow<Int>

    suspend fun getCollection(type: CollectionType): Flow<List<Movie>>
}