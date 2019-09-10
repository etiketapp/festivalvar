package com.example.festivalvar.ui.home.festivalviewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_main_festival_item.view.*

class FestivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: FestivalModel, listener: FestivalClickListener) = with(itemView) {

        /*
                tvProfileNameItem.text = model.title
                tvProfileDetailItem.text = model.title*/

        //if(model.image != null)ivFestivalItem.load(model.image.url!!)
        Glide.with(context).load(model.image?.url).into(ivFestivalItem)
        tvFestivalTitle.text = model.title
        tvFestivalSubtitle.text = model.sub_title
        tvFestivalCost.text = (model.price + " TL")
        tvFesivalLocation.text = model.place
        if(model.distance != null)tvFestivalDistance.text = model.distance.toString()
        tvFestivalDate.text = model.start_date
        btnCategory.text = model.category.title


        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface FestivalClickListener {
    fun onClick(model: FestivalModel)
}
