package com.example.news.room.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavedNewsTable")
data class SavedNews(
    @PrimaryKey(autoGenerate = true)
    val _ID: Int,
    @ColumnInfo(name = "title")
    @NonNull
    val title:String="",
    @ColumnInfo(name = "description")
    @NonNull
    val description:String="",
    @ColumnInfo(name = "link")
    @NonNull
    val link:String="",
)
