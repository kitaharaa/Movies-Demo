package com.kit.moviestest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kit.moviestest.data.room.entity.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movies")
    suspend fun getAllMovies(): List<Movie>?

    @Query("SELECT * FROM Movies m ORDER BY m.title")
    suspend fun getSortedByTitle(): List<Movie>?

    @Query("SELECT * FROM Movies m ORDER BY m.description") //todo change database and sort by date
    suspend fun getSortedByDate(): List<Movie>?

    @Query("SELECT * FROM Movies m WHERE m.id =:id")
    suspend fun getItemById(id: Int): Movie?

    @Insert
    suspend fun insertMovie(movie: List<Movie>)

    @Update
    suspend fun updateMovie(movie: Movie)
}
