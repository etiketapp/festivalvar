package com.example.festivalvar.ui.draws

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.ui.home.festivalviewholder.FestivalClickListener
import com.example.festivalvar.ui.home.festivalviewholder.FestivalViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate

class DrawsAdapter(private val items : ArrayList<DrawsModel> = arrayListOf(), private val listener: DrawsClickListener) : RecyclerView.Adapter<DrawsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawsViewHolder =
        DrawsViewHolder(parent.inflate(R.layout.row_main_festival_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DrawsViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): DrawsModel = items[position]

    fun add(list: ArrayList<DrawsModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}