package com.example.news.repositories

import android.content.Context
import com.example.news.datasource.RemoteDataSource
import com.example.news.models.News
import com.example.news.room.db.NewsLocalDataBase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataBase: NewsLocalDataBase,
    @ApplicationContext private val context: Context
) {

    fun getNewsByCategory(
        category:String?,
        onSuccess:(newslist:ArrayList<News>)->Unit,
        onError:(error:String?)->Unit
    ){
        remoteDataSource.getNewsDataByCategory(context,category,onSuccess,onError)
    }

    fun getAllNews(
        onSuccess:(newslist:ArrayList<News>)->Unit,
        onError:(error:String?)->Unit
    ){
        remoteDataSource.getAllNewsData(context,onSuccess,onError)
    }


    fun getAllSavedNews() = localDataBase.getSavedNewsDao().getAllSavedNews()

    suspend fun saveANews(news: News){
        localDataBase.getSavedNewsDao().insertSavedNews(news)
    }

    suspend fun unsavedANews(news: News){
        localDataBase.getSavedNewsDao().removeSavedNews(news)
    }

    suspend fun clearAllSavedNews(){
        localDataBase.getSavedNewsDao().deleteAllSavedNews()
    }
}