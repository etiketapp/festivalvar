package com.example.festivalvar.ui.profile.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.data.remote.model.user.commentedfestivals.CommentedFestivalModel
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_main_festival_item.view.*

class FestivalCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: CommentedFestivalModel, listener: FestivalCommentsClickListener) = with(itemView) {

        ivFestivalItem.load(model.festival?.galleries?.get(0)?.image?.url!!)
        //Glide.with(context).load(model.image?.url).into(ivFestivalItem)
        tvFestivalTitle.text = model.festival.title
        tvFestivalSubtitle.text = model.festival.sub_title
        tvFestivalCost.text = (model.festival.price + " TL")
        tvFesivalLocation.text = model.festival.place
        if(model.festival.distance != null)tvFestivalDistance.text = model.festival.distance.toString()
        tvFestivalDate.text = model.festival.start_date
        //btnCategory.text = model.festival.category.title


        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface FestivalCommentsClickListener {
    fun onClick(model: CommentedFestivalModel)
}

