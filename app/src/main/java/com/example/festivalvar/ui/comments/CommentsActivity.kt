package com.example.festivalvar.ui.comments

import android.view.View
import com.example.festivalvar.R
import com.example.festivalvar.ui.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsActivity : BaseActivity(), ICommentsNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_comments

    private val viewModel by viewModel<CommentsViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        tvToolbarTitle.visibility = View.VISIBLE
        tvToolbarTitle.text = "Yorumlar"

        iv_back.visibility = View.VISIBLE
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }
    }

}
