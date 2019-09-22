package com.example.festivalvar.ui.comments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.data.remote.model.comments.CommentsModel
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_comments_item.view.*

class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: CommentsModel, listener: CommentsClickListener) = with(itemView) {

        /*
                tvProfileNameItem.text = model.title
                tvProfileDetailItem.text = model.title*/

        tvCommentsFullname.text = model.user?.full_name
        model.user?.image?.url?.let { ivCommentProfile.load(it) }
        tvCommentsContent.text = model.comment
        tvCommentsDate.text = model.created_at


        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface CommentsClickListener {
    fun onClick(model: CommentsModel)
}