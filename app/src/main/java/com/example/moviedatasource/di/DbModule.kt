package com.example.moviedatasource.di

import android.content.Context
import androidx.room.Room
import com.example.moviedatasource.data.local.ActorDao
import com.example.moviedatasource.data.local.CollectionDao
import com.example.moviedatasource.data.local.MovieDao
import com.example.moviedatasource.data.local.TMDBDatabase
import com.example.moviedatasource.utils.Config.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object DbModule {

    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext context: Context): TMDBDatabase {
        return Room.databaseBuilder(
            context,
            TMDBDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideMovieDao(db: TMDBDatabase): MovieDao = db.moviesDao()

    @Provides
    fun provideMovieCollectionDao(db: TMDBDatabase): CollectionDao = db.movieCollectionDao()

    @Provides
    fun provideActorDao(db: TMDBDatabase): ActorDao = db.actorDao()

}