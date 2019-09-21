package com.example.festivalvar.ui.profile.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_main_festival_item.view.*

class FestivalDrawsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: UserDraws, listener: FestivalDrawsClickListener) = with(itemView) {

        /*
                tvProfileNameItem.text = model.title
                tvProfileDetailItem.text = model.title*/
        ivFestivalItem.load(model.draw?.galleries?.get(0)?.image?.url!!)
        //Glide.with(context).load(model.image?.url).into(ivFestivalItem)
        tvFestivalTitle.text = model.draw.title
        tvFestivalSubtitle.text = model.draw.sub_title
        //tvFestivalCost.text = (model.draw.price + " TL")
        //tvFesivalLocation.text = model.draw.place
        //if(model.draw.distance != null)tvFestivalDistance.text = model.draw.distance.toString()
        //tvFestivalDate.text = model.draw.start_date
        //btnCategory.text = model.festival.category.title

        tvFestivalCost.visibility = View.GONE
        tvFesivalLocation.visibility = View.GONE
        tvFestivalDistance.visibility = View.GONE
        tvFestivalDate.visibility = View.GONE
        btnCategory.visibility = View.GONE


        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface FestivalDrawsClickListener {
    fun onClick(model: UserDraws)
}