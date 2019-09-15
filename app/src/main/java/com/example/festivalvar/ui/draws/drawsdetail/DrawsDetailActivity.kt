package com.example.festivalvar.ui.draws.drawsdetail

import android.annotation.SuppressLint
import android.view.View
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.FestivalModel.Galleries
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.festivaldetail.SliderViewPagerAdapter
import kotlinx.android.synthetic.main.activity_draws_detail.*
import kotlinx.android.synthetic.main.activity_festival_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrawsDetailActivity : BaseActivity(), IDrawsDetailNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_draws_detail

    private val viewModel by viewModel<DrawsDetailViewModel>()

    private var drawsData: DrawsModel? = null

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {

        iv_back.visibility = View.VISIBLE

        val drawsModel: DrawsModel by lazy {
            intent?.getParcelableExtra("fromDrawsFragmentToDrawsDetail") as DrawsModel
        }

        drawsData = drawsModel

        initSlider(drawsData!!.galleries!!)
        initData(drawsData!!)

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }
    }


    private fun initSlider(data: ArrayList<Galleries>) {
        val adapter = SliderViewPagerAdapter(applicationContext, data)
        pagerDrawsDetail?.adapter = adapter
        indicatorDraw?.setViewPager(pagerDrawsDetail)

    }

    @SuppressLint("SetTextI18n")
    fun initData(data: DrawsModel){

        tvDrawTitle.text = data.title
        tvDrawSubtitle.text = data.sub_title
        tvDrawDetailContent.text = data.content
        tvDrawDetailDateStart.text = data.last_date
        tvJoinCount.text = "Katılanlar: "+ data.draw_users_count

    }

}
