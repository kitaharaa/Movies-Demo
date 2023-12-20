package com.kit.moviestest.di.modules

import android.content.Context
import androidx.room.Room
import com.kit.moviestest.data.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "Pre-populated Movies"
    )
        .createFromAsset("Movie Unique Database")
        .build()

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()
}