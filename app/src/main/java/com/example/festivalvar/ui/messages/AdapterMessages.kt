package com.example.festivalvar.ui.messages

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.messages.MessageIndex
import com.mobillium.birebirdiyet.utils.extensions.inflate

class AdapterMessages(private val items : ArrayList<MessageIndex> = arrayListOf(), private val listener: MessagesClickListener) : RecyclerView.Adapter<MEssagesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MEssagesViewHolder =
        MEssagesViewHolder(parent.inflate(R.layout.row_messages_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MEssagesViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): MessageIndex = items[position]

    fun add(list: ArrayList<MessageIndex>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}