package com.example.moviedatasource.data.local.entity

import androidx.room.*

/**
 * A data class to represent a movie trailer.
 * @property movieId is used to link the trailer to a movie
 */
@Entity(
    tableName = "trailers",
    indices = [Index(value = ["movie_id"])],
    foreignKeys = [
        ForeignKey(
            entity = Movie::class,
            parentColumns = ["movie_id"],
            childColumns = ["movie_id"]
        ),
    ]
)
data class MovieTrailer(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "key")
    val youtubeKey: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int,
) {
}