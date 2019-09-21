package com.example.festivalvar.ui.profile.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.user.commentedfestivals.CommentedFestivalModel
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.example.festivalvar.ui.profile.viewholders.FestivalCommentViewHolder
import com.example.festivalvar.ui.profile.viewholders.FestivalCommentsClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalLikeClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalLikeViewHolder
import com.mobillium.birebirdiyet.utils.extensions.inflate

class FestivalCommentAdapter(private val items : ArrayList<CommentedFestivalModel> = arrayListOf(), private val listener: FestivalCommentsClickListener) : RecyclerView.Adapter<FestivalCommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalCommentViewHolder =
        FestivalCommentViewHolder(parent.inflate(R.layout.row_main_festival_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FestivalCommentViewHolder, position: Int) =
        holder.bind1(getItem(position), listener)

    private fun getItem(position: Int): CommentedFestivalModel = items[position]

    fun add(list: ArrayList<CommentedFestivalModel>) {
        items.addAll(list)
        notifyDataSetChanged()
    }
}