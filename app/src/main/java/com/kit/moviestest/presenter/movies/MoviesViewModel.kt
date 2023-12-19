package com.kit.moviestest.presenter.movies

import androidx.lifecycle.ViewModel
import com.kit.moviestest.data.room.dao.MovieDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val dao: MovieDao
) : ViewModel() {
    val moviesFlow  = dao.getAllMovies()
}