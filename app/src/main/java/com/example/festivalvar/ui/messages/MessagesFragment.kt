package com.example.festivalvar.ui.messages

import android.view.View
import androidx.lifecycle.Observer
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.messages.MessageIndex
import com.example.festivalvar.ui.base.BaseFragment
import com.example.festivalvar.ui.draws.DrawsAdapter
import com.example.festivalvar.ui.messages.messagedetail.MessageDetailActivity
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MessagesFragment : BaseFragment(), IMessagesFragmentNavigator, MessagesClickListener {


    override val layoutId: Int
        get() = R.layout.fragment_messages

    private val viewModel by viewModel<MessagesFragmentViewModel>()

    private val messageAdapter by lazy { AdapterMessages(arrayListOf(), this) }


    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        tvToolbarTitle.visibility = View.VISIBLE
        tvToolbarTitle.setText("Mesajlar")

        observeViewModel()
        viewModel.getMessageList()
    }

    override fun initListener() {
    }

    fun initMessages(data: ArrayList<MessageIndex>){
        rvMessageList.adapter = messageAdapter
        messageAdapter.add(data)

    }

    fun observeViewModel(){
        viewModel.messageDataList.observe(this, Observer {
            initMessages(it)
        })
    }

    override fun onClick(model: MessageIndex) {
        activity!!.launchActivity<MessageDetailActivity> {
            this.putExtra("fromMessageFragmentToMessageDetail", model)
        }
    }

}
