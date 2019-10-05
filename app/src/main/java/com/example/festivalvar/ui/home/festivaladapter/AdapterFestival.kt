package com.example.festivalvar.ui.home.festivaladapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.ui.home.festivalviewholder.FestivalClickListener
import com.example.festivalvar.ui.home.festivalviewholder.FestivalLikeClickListener
import com.example.festivalvar.ui.home.festivalviewholder.FestivalViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate

class AdapterFestival(private val items : ArrayList<FestivalModel> = arrayListOf(),
                      private val listener: FestivalClickListener,
                      private val listenerLike: FestivalLikeClickListener) : RecyclerView.Adapter<FestivalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalViewHolder =
        FestivalViewHolder(parent.inflate(R.layout.row_main_festival_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FestivalViewHolder, position: Int) =
        holder.bind1(getItem(position), listener, listenerLike)
    private fun getItem(position: Int): FestivalModel = items[position]

    fun add(list: ArrayList<FestivalModel>) {
        items.addAll(list)
        notifyDataSetChanged()
    }


}