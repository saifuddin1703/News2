package com.example.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.NewsItemLayoutBinding
import com.example.news.databinding.SavedNewsItemLayoutBinding
import com.example.news.models.News

class SavedNewsAdapter(private var newsList: List<News>,
                       private val onUnSavedClicked: (News) -> Unit):
    RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder>() {

    inner class SavedNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = SavedNewsItemLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_news_item_layout,parent,false)
        return SavedNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val newsItem = newsList[position]

        holder.binding.newsTitle.text = newsItem.title
        Glide.with(holder.itemView.context).load(newsItem.image).into(holder.binding.newsImage)
        holder.binding.unSaveButton.setOnClickListener {
            onUnSavedClicked(newsItem)
        }
    }

    fun updateNewsList(updatedNewsList: List<News>){
        newsList = updatedNewsList
        notifyDataSetChanged()
    }
    override fun getItemCount() = newsList.size
}