package com.example.festivalvar.ui.messages.messagedetail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.messages.MessageIndex
import com.example.festivalvar.data.remote.model.messages.messagedetail.MessageDetailModel
import com.example.festivalvar.data.remote.model.messages.sendmodel.MessageSendModelRequest
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.messages.AdapterMessages
import kotlinx.android.synthetic.main.activity_message_detail.*
import kotlinx.android.synthetic.main.register_screen.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageDetailActivity : BaseActivity(), IMessageDetailNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_message_detail

    private val viewModel by viewModel<MessageDetailViewModel>()

    private val messageDetailAdapter by lazy { MessageDetailAdapter(arrayListOf()) }
    var messagesData = MutableLiveData<MessageDetailModel>()
    var messagesDataUpdated = arrayListOf<MessageDetailModel>()


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

        observeMessageDetailViewModel()
        messageModel.user_two_id?.let { viewModel.getMessageDetailList(it) }

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

        icSendMessage.setOnClickListener {
            val etMessage = etWriteMessage.text.toString()
            val request = MessageSendModelRequest( "$etMessage", messageModel.user_two_id!!)
            viewModel.postSendMessage(request)
            viewModel.getMessageDetailList(messageModel.user_two_id!!)
        }
    }

    fun initMessageDetail(data: ArrayList<MessageDetailModel>){
        rvMessadeDetailList.adapter = messageDetailAdapter
        messageDetailAdapter.add(data)

    }

    fun observeMessageDetailViewModel(){
        viewModel.messageDetailDataList.observe(this, Observer {
            initMessageDetail(it)

        })
    }
    fun observeMessages(){

        messagesData.observe(this, object: Observer<MessageDetailModel> {
            override fun onChanged(t: MessageDetailModel?) {

            }
        })

    }

}
