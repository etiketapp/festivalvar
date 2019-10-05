package com.example.festivalvar.ui.home.festivalviewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_main_festival_item.view.*
import kotlinx.android.synthetic.main.row_slider_vp_item.view.*

class FestivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: FestivalModel, listener: FestivalClickListener, listenerLike: FestivalLikeClickListener) = with(itemView) {

        /*
                tvProfileNameItem.text = model.title
                tvProfileDetailItem.text = model.title*/

        if(!model.galleries.isNullOrEmpty() && model.galleries[0].image.url != null)ivFestivalItem.load(model.galleries[0].image.url!!)
        //Glide.with(context).load(model.image?.url).into(ivFestivalItem)
        tvFestivalTitle.text = model.title
        tvFestivalSubtitle.text = model.sub_title
        tvFestivalCost.text = (model.price + " TL")
        tvFesivalLocation.text = model.place
        if(model.distance != null)tvFestivalDistance.text = model.distance.toString()
        tvFestivalDate.text = model.start_date
        btnCategory.text = model.category?.title
        if(model.is_liked!!){
            ivLike.load(R.drawable.ic_heart_full)
        } else {
            ivLike.load(R.drawable.ic_heart_empty)

        }


        itemView.setOnClickListener {
            listener.onClick(model)
        }

        ivLikeBackground.setOnClickListener {
            listenerLike.onClickLike(model)

            if(model.is_liked!!){
                ivLike.load(R.drawable.ic_heart_empty)
            } else {
                ivLike.load(R.drawable.ic_heart_full)

            }
        }
    }
}


interface FestivalClickListener {
    fun onClick(model: FestivalModel)
}


interface FestivalLikeClickListener {
    fun onClickLike(model: FestivalModel)
}
