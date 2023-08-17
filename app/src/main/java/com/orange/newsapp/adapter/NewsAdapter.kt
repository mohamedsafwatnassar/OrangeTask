package com.orange.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orange.domain.entity.Article
import com.orange.newsapp.databinding.ItemNewsBinding
import com.orange.newsapp.utils.Constant

class NewsPagingAdapter(private val itemNewClickCallBack: (article: Article) -> Unit) :
    PagingDataAdapter<Article, NewsPagingAdapter.NewsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // inner class means we can access parent class' methods and properties like 'getItem'

        init {

            binding.apply {
                root.setOnClickListener {
                    val position = absoluteAdapterPosition
                    val newsItem = getItem(position)
                    itemNewClickCallBack.invoke(newsItem!!)
                }
            }
        }

        fun bind(holder: NewsViewHolder, article: Article) {
            binding.apply {
                Constant.loadImage(holder.itemView.context, article.urlToImage ?: "", imgNews)
                title.text = article.title
                // source.text = article.source.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(holder, currentItem)
        }
    }
}
