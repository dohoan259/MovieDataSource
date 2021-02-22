package com.example.moviedatasource.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.moviedatasource.data.model.CollectionNames.FAVOURITES_NAME
import com.example.moviedatasource.data.model.CollectionNames.IN_THEATRES_NAME
import com.example.moviedatasource.data.model.CollectionNames.POPULAR_NAME
import com.example.moviedatasource.data.model.CollectionNames.TOP_RATED_NAME
import com.example.moviedatasource.data.model.CollectionNames.WATCHLIST_NAME

@Entity(tableName = "collections")
data class MovieCollection(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name="contents")
    val contents: List<Int>
) {
    @Ignore
    var movies: List<Movie>? = null
}

object CollectionNames {
    const val FAVOURITES_NAME = "favourite"
    const val WATCHLIST_NAME = "watchlist"
    const val POPULAR_NAME = "popular"
    const val TOP_RATED_NAME = "top-rated"
    const val IN_THEATRES_NAME = "in-theatres"
}

sealed class CollectionType(val name: String) {
    object Favourite: CollectionType(FAVOURITES_NAME)
    object Watchlist: CollectionType(WATCHLIST_NAME)
    object Popular: CollectionType(POPULAR_NAME)
    object TopRated: CollectionType(TOP_RATED_NAME)
    object InTheatres: CollectionType(IN_THEATRES_NAME)
}