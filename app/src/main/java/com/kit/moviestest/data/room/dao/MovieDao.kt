package com.kit.moviestest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kit.moviestest.data.room.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movies")
    fun getAllMovies(): Flow<List<Movie>?>

    @Query("SELECT * FROM Movies")
    fun getAllMoviesList(): List<Movie>?

    @Insert
    suspend fun insertMovie(movie: List<Movie>)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("DELETE FROM Movies")
    suspend fun deleteAllMovies()
}
