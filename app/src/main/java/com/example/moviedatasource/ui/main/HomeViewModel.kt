package com.example.moviedatasource.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedatasource.data.Resource
import com.example.moviedatasource.data.model.Movie
import com.example.moviedatasource.data.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getPopularMovies(): LiveData<Resource<List<Movie>>> {
        return movieRepo.getPopularMovies()
            .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)
    }
}