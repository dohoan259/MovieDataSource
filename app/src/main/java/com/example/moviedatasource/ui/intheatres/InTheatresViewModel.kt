package com.example.moviedatasource.ui.intheatres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatasource.data.local.entity.CollectionType
import com.example.moviedatasource.data.repo.MovieRepo
import com.example.moviedatasource.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class InTheatresViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    private val _viewState = MutableLiveData<UIState.InTheatresScreenState>()

    val viewState: LiveData<UIState.InTheatresScreenState> = _viewState

    init {
        viewModelScope.launch {
            movieRepo.getCollection(CollectionType.InTheatres)
                .flowOn(Dispatchers.IO)
                .collect {
                    _viewState.value = UIState.InTheatresScreenState(
                        inTheatresMoviesResource = it,
                        countryCode = "",
                        countryName = ""
                    )
                }
        }
    }
}