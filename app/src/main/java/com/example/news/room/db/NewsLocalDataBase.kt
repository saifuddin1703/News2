package com.example.news.room.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news.models.News
import com.example.news.room.daos.SavedNewsDao
import com.example.news.room.models.SavedNews


@Database( version = 1,entities = [News::class,SavedNews::class])
abstract class NewsLocalDataBase:RoomDatabase() {
    abstract fun getSavedNewsDao() : SavedNewsDao
//    abstract fun getSavedNewsDao() : SavedNewsDao

    companion object{
        private var roomDatabase: NewsLocalDataBase?= null
        fun getInstance(context:Context) : NewsLocalDataBase {
            return roomDatabase?: Room.databaseBuilder(context,
                    NewsLocalDataBase::class.java,
                    "NewsDatabase"
                )
                .build()
        }
    }
}
