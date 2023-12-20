package com.kit.moviestest.presenter.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kit.moviestest.data.room.dao.MovieDao
import com.kit.moviestest.data.room.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dao: MovieDao,
) : ViewModel() {

    fun pushValueWithId(id: Int?): Flow<Movie?> {
        return id?.let {
            dao.getItemById(it)
        } ?: flow{
            emit(null)
        }
    }

    fun updateFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.invertItemBoolean(id = id)
        }
    }
}