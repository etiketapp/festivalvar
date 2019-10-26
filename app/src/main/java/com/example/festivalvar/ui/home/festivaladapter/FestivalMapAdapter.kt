package com.example.festivalvar.ui.home.festivaladapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.ui.home.festivalviewholder.FestivalMapClickListener
import com.example.festivalvar.ui.home.festivalviewholder.FestivalMapViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate

class FestivalMapAdapter(private val items : ArrayList<FestivalModel> = arrayListOf(), private val listener: FestivalMapClickListener) : RecyclerView.Adapter<FestivalMapViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalMapViewHolder =
        FestivalMapViewHolder(parent.inflate(R.layout.row_festival_map_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FestivalMapViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): FestivalModel = items[position]

    fun add(list: ArrayList<FestivalModel>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun getPositionById(id: Int) : Int {

        items.forEachIndexed { index, element ->
            if (element.id == id) {
                return index
            }
        }

        return 0
    }
}
