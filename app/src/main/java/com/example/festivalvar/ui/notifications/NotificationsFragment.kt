package com.example.festivalvar.ui.notifications

import com.example.festivalvar.R
import com.example.festivalvar.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseFragment(), INotificationFragmentNavigator {
    override val layoutId: Int
        get() = R.layout.fragment_notifications

    private val viewModel by viewModel<NotificationsViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
    }

    override fun initListener() {
    }

}
