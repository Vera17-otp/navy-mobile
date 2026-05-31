package com.example.vera_navy.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vera_navy.Message.MessageModel
import com.example.vera_navy.databinding.ItemMessageBinding

class MessageAdapter(private val listMessage: List<MessageModel>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = listMessage[position]
        holder.binding.tvName.text = message.name
        holder.binding.tvMessage.text = message.message

        Glide.with(holder.itemView.context)
            .load(message.imageUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.binding.ivProfile)
    }

    override fun getItemCount(): Int = listMessage.size
}