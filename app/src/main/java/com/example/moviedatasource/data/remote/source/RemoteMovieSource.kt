package com.example.moviedatasource.data.remote.source

import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.Cast
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieTrailer
import com.example.moviedatasource.data.remote.service.AccountService
import com.example.moviedatasource.data.remote.service.MovieService
import com.example.moviedatasource.data.remote.service.SearchService
import com.example.moviedatasource.utils.toMovie
import com.example.moviedatasource.utils.toMovieTrailer
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteMovieSource @Inject constructor(
    private val movieService: MovieService,
    private val accountService: AccountService,
    private val searchService: SearchService,
) {

    suspend fun getMovieDetails(id: Int): Resource<Movie> {
        return when (val response = movieService.getMovieDetails(id)) {
            is NetworkResponse.Success -> {
                Resource.Success(response.body.toMovie())
            }
            is NetworkResponse.ServerError -> {
                Resource.Error(response.body?.statusMessage ?: "Server Error")
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(response.error.localizedMessage ?: "Network Error")
            }
            else ->
                Resource.Error("Unknown Error")
        }
    }

    suspend fun getMovieCast(movieId: Int): Flow<Resource<Cast>> = flow {
//        when (val response = movieService.getCreditsForMovie(movieId).await()) {
//            is NetworkResponse.Success -> {
//                Resource.Success(
//                    Cast(
//                        castMembersIds = response.body.cast.map { it.id },
//                        movieId = movieId
//                    ).apply {
//                        castMembers = response.body.cast.map { it.toActor() }
//                    }
//                )
//            }
//            is NetworkResponse.ServerError -> {
//                Resource.Error<Cast>(response.body?.statusMessage ?: "Server Error")
//            }
//            is NetworkResponse.NetworkError -> {
//                Resource.Error(response.error.localizedMessage ?: "Server Error")
//            }
//        }

    }

    suspend fun getMovieTrailer(movieId: Int): Resource<List<MovieTrailer>> {
        return when (val movieVideosResponse = movieService.getVideosForMovie(movieId).await()) {
            is NetworkResponse.Success -> {
                val trailer = movieVideosResponse
                    .body
                    .results
                    .filter { it.site == "YouTube" && it.type == "Trailer" }
                    .map { it.toMovieTrailer(movieId) }
                Resource.Success(trailer)
            }
            is NetworkResponse.ServerError -> {
                Resource.Error(
                    movieVideosResponse.body?.statusMessage ?: "Server Error"
                )
            }
            is NetworkResponse.NetworkError -> {
                Resource.Error(movieVideosResponse.error.localizedMessage ?: "Network Error")
            }
            else -> Resource.Error("Unknown Error")
        }
    }
}