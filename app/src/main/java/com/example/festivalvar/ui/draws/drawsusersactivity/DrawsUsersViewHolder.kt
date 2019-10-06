package com.example.festivalvar.ui.draws.drawsusersactivity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_festival_likes_item.view.*

class DrawsUsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: UserDraws, listener: UserDrawsClickListener) = with(itemView) {

        model.user?.image?.url?.let { ivLikesProfile.load(it) }
        tvLikessFullname.text = model.user?.full_name

        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface UserDrawsClickListener {
    fun onClick(model: UserDraws)
}