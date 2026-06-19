package com.example.vera_navy.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vera_navy.databinding.ItemNewsBinding
import com.example.vera_navy.model.Article

class NewsAdapter(private var articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.tvNewsTitle.text = article.title
        holder.binding.tvNewsDesc.text = article.contentSnippet

        // PERBAIKAN: Ubah dari article.image?.large menjadi article.image langsung
        Glide.with(holder.itemView.context)
            .load(article.image)
            .into(holder.binding.ivNewsImage)
    }



    override fun getItemCount(): Int = articles.size

    // Fungsi untuk memperbarui data list setelah memanggil API
    fun updateData(newArticles: List<Article>) {
        this.articles = newArticles
        notifyDataSetChanged()
    }
}
