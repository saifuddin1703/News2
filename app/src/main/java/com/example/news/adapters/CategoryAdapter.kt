package com.example.news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.CategoryLayoutBinding
import com.example.news.models.Category

class CategoryAdapter(private val categoryList:List<Category>,
                      private val onCategorySelected: (category:String)->Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var selectedIndex = 0
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = CategoryLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_layout,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categoryList[position]
//        holder.binding.backgroundImage.setImageResource(item.imageResourceID)
        if (selectedIndex == position){
            holder.binding.root.setBackgroundResource(R.drawable.category_background_selected)
        }else{
            holder.binding.root.setBackgroundResource(R.drawable.category_background)
        }
        holder.binding.root.setOnClickListener {
            selectedIndex = position
            onCategorySelected(item.name)
            notifyDataSetChanged()
        }
        holder.binding.categoryName.text = item.name
    }

    override fun getItemCount() = 6
}