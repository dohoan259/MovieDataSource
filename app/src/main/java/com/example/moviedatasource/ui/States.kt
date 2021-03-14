package com.example.moviedatasource.ui

import android.view.View
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.Actor
import com.example.moviedatasource.data.local.entity.Movie
import com.example.moviedatasource.data.local.entity.MovieTrailer

sealed class UIState {
    data class HomeScreenState(
        val popularMoviesResource: Resource<List<Movie>>,
        val topRatedMoviesResource: Resource<List<Movie>>,
        val searchResultsResource: Resource<List<Movie>>?
    ): UIState()

    data class InTheatresScreenState(
        val inTheatresMoviesResource: Resource<List<Movie>>,
        val countryCode: String,
        val countryName: String
    ): UIState()

    data class DetailsScreenState(val movieId: Int = -1,
                                  val transitionName: String? = null,
                                  val sharedView: View? = null,
                                  val movieResource: Resource<Movie>,
                                  val accountStatesResource: Resource<AccountState>,
                                  val trailerResource: Resource<MovieTrailer>,
                                  val castResource: List<Resource<Actor>>,
                                  val similarMoviesResource: Resource<List<Movie>>): UIState()
}