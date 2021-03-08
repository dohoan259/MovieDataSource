package com.example.moviedatasource.ui

import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.local.entity.Movie

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
}