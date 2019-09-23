package com.example.festivalvar.ui.home.festivallikes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.comments.CommentsModel
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.mobillium.birebirdiyet.utils.extensions.inflate

class FestivalLikesAdapter(private val items : ArrayList<FestivalLikes> = arrayListOf(), private val listener: FestivalLikesClickListener) : RecyclerView.Adapter<FestivalLikesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalLikesViewHolder =
        FestivalLikesViewHolder(parent.inflate(R.layout.row_festival_likes_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FestivalLikesViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): FestivalLikes = items[position]

    fun add(list: ArrayList<FestivalLikes>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}