package com.example.festivalvar.ui.home.festivallikes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.data.remote.model.comments.CommentsModel
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_comments_item.view.*
import kotlinx.android.synthetic.main.row_festival_likes_item.view.*

class FestivalLikesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: FestivalLikes, listener: FestivalLikesClickListener) = with(itemView) {

        model.user?.image?.url?.let { ivLikesProfile.load(it) }
        tvLikessFullname.text = model.user?.full_name



        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface FestivalLikesClickListener {
    fun onClick(model: FestivalLikes)
}