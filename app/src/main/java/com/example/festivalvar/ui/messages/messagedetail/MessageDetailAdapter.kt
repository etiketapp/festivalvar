package com.example.festivalvar.ui.messages.messagedetail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.messages.messagedetail.MessageDetailModel
import com.mobillium.birebirdiyet.utils.extensions.inflate

class MessageDetailAdapter(private val items : ArrayList<MessageDetailModel> = arrayListOf()) : RecyclerView.Adapter<MessageDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageDetailViewHolder =
        MessageDetailViewHolder(parent.inflate(R.layout.row_message_detail_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MessageDetailViewHolder, position: Int) =
        holder.bind1(getItem(position))

    private fun getItem(position: Int): MessageDetailModel = items[position]

    fun add(list: ArrayList<MessageDetailModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}