package com.example.moviedatasource.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedatasource.data.model.Actor
import com.example.moviedatasource.data.model.Cast
import com.example.moviedatasource.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovie(movieId: Int): Flow<Movie>

    @Query("SELECT * from actors WHERE id IN (:movieId)")
    fun getActorsForMovie(movieId: Int): Flow<List<Actor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(vararg movies: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCast(cast: Cast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCasts(vararg cast: Cast)

    @Query("SELECT COUNT(*) FROM movies WHERE id = :id")
    fun isMovieInDatabase(id: Int): Flow<Int>
}