package com.kit.moviestest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kit.moviestest.domain.converter.PhotoConverter
import com.kit.moviestest.domain.converter.TimeConverters
import com.kit.moviestest.data.room.dao.MovieDao
import com.kit.moviestest.data.room.entity.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(PhotoConverter::class, TimeConverters::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}