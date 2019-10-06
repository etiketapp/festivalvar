package com.example.festivalvar.ui.messages.messagedetail

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.messages.messagedetail.MessageDetailModel
import com.example.festivalvar.utils.PrefUtils
import kotlinx.android.synthetic.main.row_message_detail_item.view.*

class MessageDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: MessageDetailModel) = with(itemView) {

        /*
                tvProfileNameItem.text = model.title
                tvProfileDetailItem.text = model.title*/

        if(PrefUtils.getUserId() == model.user_one_id){
            messageContainerSend.visibility = View.VISIBLE
            tvMessageDetailSend.text = model.message

        } else {
            messageContainerTaken.visibility = View.VISIBLE
            tvMessageDetailTaken.text = model.message
        }

    }

}
