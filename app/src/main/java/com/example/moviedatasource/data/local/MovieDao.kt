package com.example.moviedatasource.data.local

import androidx.room.*
import com.example.moviedatasource.data.local.entity.Cast
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieCollectionCrossRef
import com.example.moviedatasource.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun getMovieById(movieId: Int): Flow<Movie>

    @Transaction
    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun getMovieDetail(movieId: Int): Flow<MovieDetail>

//    @Query("SELECT * from actors WHERE movie_id IN (:movieId)")
//    fun getActorsForMovie(movieId: Int): Flow<List<Actor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(vararg movies: Movie): Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCollectionCross(collectionCrossRef: MovieCollectionCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCollectionCross(vararg collectionCrossRef: MovieCollectionCrossRef): Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCast(cast: Cast): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCasts(vararg cast: Cast): Array<Long>

    @Query("SELECT COUNT(*) FROM movies WHERE movie_id = :id")
    fun isMovieInDatabase(id: Int): Flow<Int>
}