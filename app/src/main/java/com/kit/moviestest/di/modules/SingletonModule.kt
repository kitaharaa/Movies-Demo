package com.kit.moviestest.di.modules

import android.content.Context
import androidx.room.Room
import com.kit.moviestest.data.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "Pre-populated Movies"
    )
        .createFromAsset("Pre-populated Movies")
        .build()

    @Provides
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()
}