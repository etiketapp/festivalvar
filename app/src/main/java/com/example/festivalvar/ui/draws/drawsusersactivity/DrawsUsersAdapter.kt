package com.example.festivalvar.ui.draws.drawsusersactivity

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.ui.home.festivallikes.FestivalLikesClickListener
import com.example.festivalvar.ui.home.festivallikes.FestivalLikesViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate

class DrawsUsersAdapter(private val items : ArrayList<UserDraws> = arrayListOf(), private val listener: UserDrawsClickListener) : RecyclerView.Adapter<DrawsUsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawsUsersViewHolder =
        DrawsUsersViewHolder(parent.inflate(R.layout.row_festival_likes_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DrawsUsersViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): UserDraws = items[position]

    fun add(list: ArrayList<UserDraws>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}