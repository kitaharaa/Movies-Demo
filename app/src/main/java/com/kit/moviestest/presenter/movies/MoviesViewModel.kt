package com.kit.moviestest.presenter.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kit.moviestest.data.room.dao.MovieDao
import com.kit.moviestest.data.room.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val dao: MovieDao,
) : ViewModel() {
    private val _moviesFlow: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)
    val moviesFlow get() = _moviesFlow.asStateFlow()

    fun pushDefault() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesFlow.value = dao.getAllMovies()
        }
    }

    fun updateSortedByTitle() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesFlow.value = dao.getSortedByTitle()
        }
    }

    fun updateSortedByDate() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesFlow.value = dao.getSortedByDate()
        }
    }
}