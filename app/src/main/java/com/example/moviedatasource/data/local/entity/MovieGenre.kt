package com.example.moviedatasource.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * A data class to represent the relationship set Movie-Genre.
 * We create a separate table for this relation because it is a MxN relation.
 * The '@Relation' annotation from is suitable only for 1xN relations.
 */
@Entity(
    tableName = "movie_genre",
    primaryKeys = ["movie_id", "genre_id"],
    indices = [Index(value = ["movie_id", "genre_id"])],
    foreignKeys = [
        ForeignKey(entity = Movie::class, parentColumns = ["movie_id"], childColumns = ["movie_id"]),
        ForeignKey(entity = Genre::class, parentColumns = ["genre_id"], childColumns = ["genre_id"])
    ]
)
data class MovieGenre(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "genre_id")
    val genreId: Int
)