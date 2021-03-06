package com.example.moviedatasource.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieCollection
import com.example.moviedatasource.data.local.entity.MovieCollectionCrossRef

data class CollectionWithMovies(
    @Embedded
    val movieCollection: MovieCollection,
    @Relation(
        parentColumn = "name",
        entityColumn = "movie_id",
        associateBy = Junction(MovieCollectionCrossRef::class)
    )
    val movies: List<Movie>
)
