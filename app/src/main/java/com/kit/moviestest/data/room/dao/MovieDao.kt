package com.kit.moviestest.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.kit.moviestest.data.room.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movies")
    suspend fun getAllMovies(): List<Movie>?

    @Query("SELECT * FROM Movies m ORDER BY m.title")
    suspend fun getSortedByTitle(): List<Movie>?

    @Query("SELECT * FROM Movies m ORDER BY m.released_date")
    suspend fun getSortedByDate(): List<Movie>?

    @Query("SELECT * FROM Movies m WHERE m.id =:id")
    fun getItemById(id: Int): Flow<Movie?>

    @Query("UPDATE Movies  SET is_in_watch_list = NOT is_in_watch_list WHERE id=:id")
    fun invertItemBoolean(id: Int)
}
