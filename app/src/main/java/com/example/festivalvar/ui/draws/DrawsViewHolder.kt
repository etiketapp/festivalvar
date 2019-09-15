package com.example.festivalvar.ui.draws

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_main_festival_item.view.*

class DrawsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: DrawsModel, listener: DrawsClickListener) = with(itemView) {

        /*
                tvProfileNameItem.text = model.title
                tvProfileDetailItem.text = model.title*/

            ivFestivalItem.load(model.galleries!![0].image.url!!)
        //Glide.with(context).load(model.image?.url).into(ivFestivalItem)
        tvFestivalTitle.text = model.title
        tvFestivalSubtitle.text = model.sub_title

        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface DrawsClickListener {
    fun onClick(model: DrawsModel)
}
