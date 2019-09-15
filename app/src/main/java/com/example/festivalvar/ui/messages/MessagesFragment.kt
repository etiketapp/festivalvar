package com.example.festivalvar.ui.messages

import android.view.View
import com.example.festivalvar.R
import com.example.festivalvar.ui.base.BaseFragment
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MessagesFragment : BaseFragment(), IMessagesFragmentNavigator {
    override val layoutId: Int
        get() = R.layout.fragment_messages
    private val viewModel by viewModel<MessagesFragmentViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        tvToolbarTitle.visibility = View.VISIBLE
        tvToolbarTitle.setText("Mesajlar")
    }

    override fun initListener() {
    }

}
