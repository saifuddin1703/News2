package com.example.news.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.R
import com.example.news.adapters.CategoryAdapter
import com.example.news.adapters.NewsAdapter
import com.example.news.databinding.FragmentCategoryBinding
import com.example.news.utils.categories
import com.example.news.viewmodels.CategoriesViewModel
import com.example.news.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : NewsViewModel by viewModels()

        binding = FragmentCategoryBinding.inflate(layoutInflater)
        binding.categoryList.adapter = CategoryAdapter(categories) {
            viewModel.setCategory(it)
        }

        viewModel.getNews().observe(viewLifecycleOwner){
            binding.newsList.adapter = NewsAdapter(
                it,
                onSavedClicked = {news ->
                    viewModel.saveANews(news)
                },
                onUnSavedClicked = {news ->
                    viewModel.unSaveANews(news)
                },
                onNewsClicked = {url->
                    val intent = Intent(this.requireActivity(),NewsDetailActivity::class.java)
                    intent.putExtra("link",url)
                    startActivity(intent)
                }
            )
        }
        return binding.root
    }
}