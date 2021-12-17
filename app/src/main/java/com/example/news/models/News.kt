package com.example.news.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "SavedNews")
data class News(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    @ColumnInfo(name = "title") var title: String="",
    @ColumnInfo(name = "link") var link: String="",
    @ColumnInfo(name = "image") var image: String=""
)
