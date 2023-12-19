package com.kit.moviestest.data.room.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val rating: Float,
    val duration: String,
    val genre: String,
    @ColumnInfo("released_date")
    val releasedDate: String,
    @ColumnInfo("trailer_link")
    val trailerLink: String,
    @ColumnInfo("is_in_watch_list")
    val isInWatchList: Boolean,
    val image: Bitmap?
)