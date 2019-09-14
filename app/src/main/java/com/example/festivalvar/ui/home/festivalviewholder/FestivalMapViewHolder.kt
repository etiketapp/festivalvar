package com.example.festivalvar.ui.home.festivalviewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_festival_map_item.view.*

class FestivalMapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: FestivalModel, listener: FestivalMapClickListener) = with(itemView) {

        //ivFestivalMapItem.load(model.image?.url!!)
        tvMapItemFestivalTitle.text = model.title
        tvMapItemFestivalDate.text = model.start_date
        tvMapItemFestivalLocation.text = model.place
        tvMapItemCost.text = model.price


        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }
}


interface FestivalMapClickListener {
    fun onClick(model: FestivalModel)
}