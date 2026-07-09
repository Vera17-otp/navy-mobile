package com.example.vera_navy.Home.Pertemuan7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.vera_navy.R

class NewsAdapter(context: Context, resource: Int, objects: List<News>) :
    ArrayAdapter<News>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        val item = getItem(position)

        val ivImage = view.findViewById<ImageView>(R.id.ivNewsImage)
        val tvTitle = view.findViewById<TextView>(R.id.tvNewsTitle)
        val tvDesc = view.findViewById<TextView>(R.id.tvNewsDesc)

        item?.let {
            tvTitle.text = it.title
            tvDesc.text = it.description
            
            // MATERI PERTEMUAN 7: Menggunakan Glide untuk load gambar
            Glide.with(context)
                .load(it.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(ivImage)
        }

        return view
    }
}
