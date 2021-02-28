package com.example.moviedatasource.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.moviedatasource.data.local.entity.*

data class MovieDetail(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_id",
        associateBy = Junction(MovieGenre::class)
    )
    val genres: List<Genre>,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movie_id"
    )
    val trailers: List<MovieTrailer>,

    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(Cast::class)
    )
    val casts: List<Cast>
)