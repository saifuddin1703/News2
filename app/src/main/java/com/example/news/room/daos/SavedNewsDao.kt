package com.example.news.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.models.News

@Dao
interface SavedNewsDao {

    @Query("SELECT * FROM SavedNews")
    fun getAllSavedNews():LiveData<List<News>>

    @Query("DELETE FROM SavedNews")
    suspend fun deleteAllSavedNews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedNews(news: News)

    @Delete
    suspend fun removeSavedNews(news: News)
}