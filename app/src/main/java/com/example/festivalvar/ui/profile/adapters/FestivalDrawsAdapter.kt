package com.example.festivalvar.ui.profile.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.example.festivalvar.ui.profile.viewholders.FestivalCommentViewHolder
import com.example.festivalvar.ui.profile.viewholders.FestivalCommentsClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalDrawsClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalDrawsViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate


class FestivalDrawsAdapter(private val items : ArrayList<UserDraws> = arrayListOf(), private val listener: FestivalDrawsClickListener) : RecyclerView.Adapter<FestivalDrawsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalDrawsViewHolder =
        FestivalDrawsViewHolder(parent.inflate(R.layout.row_main_festival_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FestivalDrawsViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): UserDraws = items[position]

    fun add(list: ArrayList<UserDraws>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}