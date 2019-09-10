package com.example.festivalvar.ui.messages

import com.example.festivalvar.R
import com.example.festivalvar.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MessagesFragment : BaseFragment(), IMessagesFragmentNavigator {
    override val layoutId: Int
        get() = R.layout.fragment_messages
    private val viewModel by viewModel<MessagesFragmentViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
    }

    override fun initListener() {
    }

}
