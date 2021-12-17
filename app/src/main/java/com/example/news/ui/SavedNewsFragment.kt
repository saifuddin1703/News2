package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.R
import com.example.news.adapters.SavedNewsAdapter
import com.example.news.databinding.FragmentSavedNewsBinding
import com.example.news.viewmodels.SavedNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    lateinit var binding: FragmentSavedNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedNewsBinding.inflate(inflater)
        val viewModel:SavedNewsViewModel by viewModels()
        val adapter = SavedNewsAdapter(listOf()){ news ->
            viewModel.unsavedANews(news)
        }
        binding.savedNewsList.adapter = adapter
        viewModel.getAllSavedNews().observe(viewLifecycleOwner){
          adapter.updateNewsList(it)
        }
        return binding.root
    }

}