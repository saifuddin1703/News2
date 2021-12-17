package com.example.news.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.models.News
import com.example.news.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
//    lateinit var repository: NewsRepository

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


}

