package com.orange.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orange.domain.entity.Article
import com.orange.newsapp.databinding.ItemNewsBinding

class NewsAdapter(
    private val itemNewClickCallBack: (orderModel: Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: List<Article> = ArrayList()

    private lateinit var binding: ItemNewsBinding

    inner class NewsViewHolder(itemView: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val imgNews = itemView.imgNews
        val title = itemView.title
        val source = itemView.source

        init {
            itemView.root.setOnClickListener {
                itemNewClickCallBack.invoke(newsList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = newsList[position]
        // bind view
        bindData(holder, article)
    }

    private fun bindData(holder: NewsViewHolder, article: Article) {
        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            // .placeholder(R.drawable.ic_placeholder_school)
            // .error(R.drawable.ic_placeholder_school)
            .into(holder.imgNews)

        holder.title.text = article.title
        holder.source.text = article.source.name
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun submitData(data: List<Article>) {
        newsList = data
        notifyDataSetChanged()
    }
}
