package com.example.festivalvar.ui.messages.messagedetail

import android.view.View
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.messages.MessageIndex
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModelRequest
import com.example.festivalvar.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_message_detail.*
import kotlinx.android.synthetic.main.register_screen.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageDetailActivity : BaseActivity(), IMessageDetailNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_message_detail

    private val viewModel by viewModel<MessageDetailViewModel>()


    val messageModel: MessageIndex by lazy {
        intent.getParcelableExtra( "fromMessageFragmentToMessageDetail") as MessageIndex
    }

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {

        iv_back.visibility = View.VISIBLE
        tvToolbarTitle.visibility = View.VISIBLE
        tvToolbarTitle.text = messageModel.user_two?.full_name



    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

        icSendMessage.setOnClickListener {
            val etMessage = etWriteMessage.text.toString()
            val request = MessageSendModelRequest( "$etMessage", messageModel.user_two_id!!)
            viewModel.postSendMessage(request)

        }
    }

}
