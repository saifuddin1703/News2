package com.example.news.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.adapters.NewsAdapter
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        Log.d("tag", "home fragment created")
    }

    override fun onStart() {
        super.onStart()
        Log.d("tag","home fragment started")
    }

    override fun onResume() {
        super.onResume()
        Log.d("tag","home fragment resumed")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewmodel:NewsViewModel by viewModels()
        val news = viewmodel.getAllNews()
        news.observe(viewLifecycleOwner,
            { newsList ->
                 binding.newsList.adapter = NewsAdapter(newsList,
                 onSavedClicked = {news->
                     viewmodel.saveANews(news)
                 },
                 onUnSavedClicked = {news ->
                     viewmodel.unSaveANews(news)
                 },
                 onNewsClicked = {url->
                     val intent = Intent(this.requireActivity(),NewsDetailActivity::class.java)
                     intent.putExtra("link",url)
                     startActivity(intent)
                 })
            })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onPause() {
        super.onPause()
        Log.d("tag","home fragment paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d("tag","home fragment stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("tag","home fragment destroyed")
    }

}