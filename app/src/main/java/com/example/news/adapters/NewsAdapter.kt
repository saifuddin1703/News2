package com.example.news.adapters

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.NewsItemLayoutBinding
import com.example.news.models.News


class NewsAdapter(private val newsList: List<News>,
                  private val onSavedClicked: (News) -> Unit,
                  private val onUnSavedClicked: (News) -> Unit,
                  private val onNewsClicked:(url:String) ->Unit):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = NewsItemLayoutBinding.bind(itemView)
        var isSaved = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout,parent,false)
        return NewsViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
//        Log.d("tag",newsList[position].title)
        holder.binding.newsTitle.text = newsItem.title
        Glide.with(holder.itemView.context).load(newsItem.image).into(holder.binding.newsImage)
        holder.binding.saveButton.setOnClickListener {
            val theme = holder.itemView.resources.newTheme()
            val grayColor = holder.itemView.resources.getColor(R.color.gray_600,theme)
            val whiteColor = holder.itemView.resources.getColor(R.color.white,theme)
            val blackColor = holder.itemView.resources.getColor(R.color.black,theme)

            if (holder.isSaved) {
                val button = holder.binding.saveButton
                button.text = holder.itemView.context.getString(R.string.save)
                button.setBackgroundColor(whiteColor)
                button.iconTint = ColorStateList.valueOf(blackColor)
                button.setTextColor(blackColor)
                holder.isSaved = false
                onUnSavedClicked(newsItem)
            }else {
                val button = holder.binding.saveButton
                button.setBackgroundColor(grayColor)
                button.text = holder.itemView.context.getString(R.string.usave)
                button.iconTint = ColorStateList.valueOf(whiteColor)
                button.setTextColor(whiteColor)
                holder.isSaved = true
                onSavedClicked(newsItem)
            }
        }

        holder.itemView.setOnClickListener {
            val url = newsItem.link
            onNewsClicked(url)
        }
        holder.binding.shareButton.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, newsItem.link)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Share Using")

            holder.itemView.context.startActivity(shareIntent)
        }
    }

    override fun getItemCount() = newsList.size
}