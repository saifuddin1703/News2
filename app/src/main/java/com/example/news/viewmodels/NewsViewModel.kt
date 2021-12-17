package com.example.news.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.News
import com.example.news.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@HiltViewModel
class NewsViewModel @Inject constructor(): ViewModel() {
    @Inject lateinit var repository: NewsRepository

    private val allNewsList : MutableLiveData<List<News>> by lazy {
        MutableLiveData<List<News>>().also{
            repository.getAllNews({
               allNewsList.value = it
            },{
                Log.d("tag", it.toString())
            })
        }
    }

    private lateinit var newsByCategory : MutableLiveData<List<News>>
    fun getAllNews() = allNewsList
    private val category: MutableLiveData<String> by lazy {
        MutableLiveData<String>(
            "Entertainment"
        )
    }

    fun setCategory(categoryName:String){
        category.value = categoryName
    }

    private val categoryNewsList : MutableLiveData<List<News>> by lazy {
        MutableLiveData<List<News>>().also {
            loadCategoryNews()
        }
    }

    fun getNews() = categoryNewsList
    private fun loadCategoryNews() {
        repository.getNewsByCategory(category.value,{
            categoryNewsList.value = it
        },{
            Log.d("tag","Error loading news")
        })
    }

    fun saveANews(news: News){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(this.coroutineContext) {
                repository.saveANews(news)
            }
            Log.d("tag","news saved and inserted to saved news table")
        }
    }

    fun unSaveANews(news: News){
        viewModelScope.launch {
            withContext(coroutineContext){
                repository.unsavedANews(news)
            }
        }
    }
}