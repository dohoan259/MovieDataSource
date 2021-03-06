package com.example.moviedatasource.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedatasource.data.local.entity.CollectionNames
import com.example.moviedatasource.data.model.CollectionWithMovies
import com.example.moviedatasource.data.local.entity.MovieCollection
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Query("SELECT COUNT(*) FROM collections WHERE name = :name")
    fun isCollectionInDatabase(name: String): Flow<Int>

    @Query("SELECT * FROM collections WHERE name = :name")
    fun getCollectionByName(name: String): Flow<MovieCollection>

    @Query("SELECT * FROM collections WHERE name = :name")
    fun getCollectionWithMovies(name: String): Flow<CollectionWithMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCollection(movieCollection: MovieCollection): Long
}