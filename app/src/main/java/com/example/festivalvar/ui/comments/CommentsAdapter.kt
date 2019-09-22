package com.example.festivalvar.ui.comments

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.comments.CommentsModel
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUser
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.ui.draws.DrawsClickListener
import com.example.festivalvar.ui.draws.DrawsViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate

class CommentsAdapter(private val items : ArrayList<CommentsModel> = arrayListOf(), private val listener: CommentsClickListener) : RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder =
        CommentsViewHolder(parent.inflate(R.layout.row_comments_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): CommentsModel = items[position]

    fun add(list: ArrayList<CommentsModel>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}