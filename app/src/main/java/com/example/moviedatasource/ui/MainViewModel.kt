package com.example.moviedatasource.ui

import androidx.lifecycle.ViewModel
import com.example.moviedatasource.data.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel()