package com.example.moviedatasource.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviedatasource.data.model.*
import com.example.moviedatasource.data.repo.Converters

@Database(
    entities = [
        Movie::class, Genre::class, Trailer::class, MovieGenre::class, Actor::class, Cast::class, MovieCollection::class,
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao

    abstract fun actorDao(): ActorDao

    abstract fun movieCollectionDao(): CollectionDao
}