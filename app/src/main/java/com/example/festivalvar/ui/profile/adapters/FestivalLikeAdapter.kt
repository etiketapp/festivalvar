package com.example.festivalvar.ui.profile.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.example.festivalvar.ui.profile.viewholders.FestivalLikeClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalLikeViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate

class FestivalLikeAdapter(private val items : ArrayList<LikedFestivalsModel> = arrayListOf(), private val listener: FestivalLikeClickListener) : RecyclerView.Adapter<FestivalLikeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalLikeViewHolder =
        FestivalLikeViewHolder(parent.inflate(R.layout.row_main_festival_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FestivalLikeViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): LikedFestivalsModel = items[position]

    fun add(list: ArrayList<LikedFestivalsModel>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}
