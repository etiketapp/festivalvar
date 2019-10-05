package com.example.festivalvar.ui.messages

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.festivalvar.data.remote.model.messages.MessageIndex
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.row_messages_item.view.*

class MEssagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind1(model: MessageIndex, listener: MessagesClickListener) = with(itemView) {

        /*
                tvProfileNameItem.text = model.title
                tvProfileDetailItem.text = model.title*/


        model.user_two?.image?.url?.let { ivMessageProfile.load(it) }
        tvMessagesFullname.text = model.user_two?.full_name
        tvMessageContent.text = model.latest_message?.message

        itemView.setOnClickListener {
            listener.onClick(model)
        }

    }

}


interface MessagesClickListener {
    fun onClick(model: MessageIndex)
}
