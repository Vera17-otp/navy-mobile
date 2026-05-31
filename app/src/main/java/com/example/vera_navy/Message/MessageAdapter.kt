package com.example.vera_navy.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.vera_navy.R
import com.google.android.material.imageview.ShapeableImageView

class MessageAdapter(private val context: Context, private val dataSource: List<MessageModel>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = dataSource.size

    override fun getItem(position: Int): Any = dataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.item_message, parent, false)

        // Sesuaikan ID dengan yang ada di item_message.xml
        val tvTitle = rowView.findViewById<TextView>(R.id.tvName)
        val tvDescription = rowView.findViewById<TextView>(R.id.tvMessage)
        val ivUser = rowView.findViewById<ShapeableImageView>(R.id.ivProfile)

        val message = getItem(position) as MessageModel

        tvTitle.text = message.name
        tvDescription.text = message.message

        // Gunakan Glide untuk menampilkan gambar
        Glide.with(context)
            .load(message.imageUrl)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(ivUser)

        return rowView
    }
}