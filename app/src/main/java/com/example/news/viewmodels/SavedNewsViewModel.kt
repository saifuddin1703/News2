package com.example.news.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.News
import com.example.news.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(){

    private val allSavedNewsList: LiveData<List<News>> = repository.getAllSavedNews()

    fun getAllSavedNews() = allSavedNewsList

    fun saveNews(news: News){
        viewModelScope.launch {
            withContext(coroutineContext){
                repository.saveANews(news)
            }
        }
    }

    fun unsavedANews(news: News){
        viewModelScope.launch {
            withContext(coroutineContext){
                repository.unsavedANews(news)
            }
        }
    }

    fun removeAllSavedNews(){
        viewModelScope.launch {
            withContext(coroutineContext){
                repository.clearAllSavedNews()
            }
        }
    }
}