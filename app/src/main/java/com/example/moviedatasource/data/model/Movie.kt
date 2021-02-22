package com.example.moviedatasource.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "release_date")
    val releaseDate: Date,
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>,
    @ColumnInfo(name = "is_adult")
    val isAdult: Boolean = false,
    @ColumnInfo(name = "budget")
    val budget: Long?,
    @ColumnInfo(name = "revenue")
    val revenue: Long?,
    @ColumnInfo(name = "genres")
    val genres: List<String>?,
    @ColumnInfo(name = "is_model_complete")
    val isModelComplete: Boolean
)