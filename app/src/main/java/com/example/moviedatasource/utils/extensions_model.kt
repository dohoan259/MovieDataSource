package com.example.moviedatasource.utils

import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.Actor
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieTrailer
import com.example.moviedatasource.data.remote.model.response.*

internal fun <T : Any> T.toResource(): Resource<T> = Resource.Success(this)

internal fun GeneralMovieResponse.toMovie(): Movie {
    return Movie(
        movieId = this.id,
        title = this.title,
        this.posterPath.getPosterUrl(),
        this.backdropPath.getBackdropUrl(),
        this.overview,
        this.voteAverage,
        this.releaseDate,
//        this.genreIds,
        this.isAdultMovie,
        null,
        null,
//        null,
        isModelComplete = false
    )
}

internal fun MovieResponse.toMovie(): Movie {
    return Movie(
        movieId = this.id,
        title = this.title,
        posterPath = this.posterPath.getPosterUrl(),
        backdropPath = this.backdropPath.getBackdropUrl(),
        overview = this.overview ?: "",
        voteAverage = this.voteAverage,
        releaseDate = this.releaseDate,
//        genreIds = this.genres.map { genrePair -> genrePair.id },
        isAdult = this.isAdult,
        budget = this.budget,
        revenue = this.revenue,
//        genres = this.genres.map { genrePair -> genrePair.name },
        isModelComplete = true
    )
}

internal fun CastMember.toActor(): Actor {
    return Actor(
        this.id,
        this.profilePath.getProfilePictureUrl(),
        this.name,
        null,
        null,
        false
    )
}

internal fun PersonResponse.toActor(): Actor {
    return Actor(
        this.id,
        this.profilePath.getProfilePictureUrl(),
        this.name,
        this.birthday,
        this.biography,
        true
    )
}

internal fun MovieVideo.toMovieTrailer(movieId: Int): MovieTrailer {
    return MovieTrailer(
        movieId = movieId,
        youtubeKey = this.key,
        id = 0
    )
}

/*
internal fun MovieStatesResponse.toAccountState(movieId: Int): AccountState {
    return AccountState(
        isWatchlisted = this.isWatchlisted ?: false,
        isFavourited = this.isFavourited ?: false,
        movieId = movieId
    )
}
*/

internal fun String.getYoutubeUrl() = "https://www.youtube.com/watch?v=$this"

internal fun String?.getPosterUrl(): String {
    return this?.let {
        "${Config.BASE_IMAGE_URL}${Config.DEFAULT_POSTER_SIZE}$it"
    } ?: ""
}

internal fun String?.getBackdropUrl(): String {
    return this?.let {
        "${Config.BASE_IMAGE_URL}${Config.DEFAULT_BACKDROP_SIZE}${this}"
    } ?: ""
}

internal fun String?.getProfilePictureUrl(): String {
    return this?.let {
        "${Config.BASE_IMAGE_URL}${Config.SMALL_PROFILE_PICTURE_SIZE}$this"
    } ?: ""
}
