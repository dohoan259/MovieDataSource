package com.example.moviedatasource.ui.moviedetail

import androidx.lifecycle.ViewModel
import com.example.moviedatasource.data.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {
}