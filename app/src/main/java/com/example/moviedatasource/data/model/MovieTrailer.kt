package com.example.moviedatasource.data.model

import androidx.room.*

/**
 * A data class to represent the relation between a movie and its trailers
 * The `@Relation` annotation from Room is suitable here because there is a
 * 1XN mapping cardinality between the two tables.
 */
@Entity(
    tableName = "movie_trailers",
    foreignKeys = [ForeignKey(
        entity = Movie::class,
        parentColumns = ["id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class MovieTrailer(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "key")
    val youtubeVideoKey: String
)