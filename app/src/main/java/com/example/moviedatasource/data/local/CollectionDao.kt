package com.example.moviedatasource.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.moviedatasource.data.model.CollectionNames
import com.example.moviedatasource.data.model.Movie
import com.example.moviedatasource.data.model.MovieCollection
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Query("SELECT COUNT(*) FROM collections WHERE name = :name")
    fun isCollectionInDatabase(name: String): Flow<Int>

    @Query("SELECT * FROM collections WHERE name = '${CollectionNames.FAVOURITES_NAME}'")
    fun getFavouriteCollection(): Flow<MovieCollection>

    @Query("SELECT * FROM collections WHERE name = '${CollectionNames.POPULAR_NAME}'")
    fun getPopularCollection(): Flow<MovieCollection>

    @Query("SELECT * FROM movies WHERE id IN (:ids)")
    fun getMoviesForCollection(ids: List<Int>): Flow<List<Movie>>

}