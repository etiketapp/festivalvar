package com.example.festivalvar.ui.draws.drawsdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.festivalvar.R
import com.example.festivalvar.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrawsDetailActivity : BaseActivity(), IDrawsDetailNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_draws_detail

    private val viewModel by viewModel<DrawsDetailViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
    }

    override fun initListener() {
    }

}
