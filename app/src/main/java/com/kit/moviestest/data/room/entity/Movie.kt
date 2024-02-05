package com.kit.moviestest.data.room.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Movies")
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val rating: Float,
    val duration: String,
    val genre: String,
    @ColumnInfo("released_date")
    val releasedDate: Date,
    @ColumnInfo("trailer_link")
    val trailerLink: String,
    val image: Bitmap?,
    @ColumnInfo("is_in_watch_list")
    val isInWatchList: Boolean,
)