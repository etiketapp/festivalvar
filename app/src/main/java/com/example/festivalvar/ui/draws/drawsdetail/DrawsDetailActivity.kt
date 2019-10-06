package com.example.festivalvar.ui.draws.drawsdetail

import android.annotation.SuppressLint
import android.view.View
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.FestivalModel.Galleries
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.draws.drawsusersactivity.DrawsUsersActivity
import com.example.festivalvar.ui.festivaldetail.SliderViewPagerAdapter
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.activity_draws_detail.*
import kotlinx.android.synthetic.main.activity_festival_detail.*
import kotlinx.android.synthetic.main.row_main_festival_item.*
import kotlinx.android.synthetic.main.row_main_festival_item.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrawsDetailActivity : BaseActivity(), IDrawsDetailNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_draws_detail

    private val viewModel by viewModel<DrawsDetailViewModel>()

    private var drawsData: DrawsModel? = null
    private var drawsProfileData: UserDraws? = null

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {

        iv_back.visibility = View.VISIBLE
        ivToolbarLogo.visibility = View.VISIBLE

        val drawsModel: DrawsModel by lazy {
            intent?.getParcelableExtra("fromDrawsFragmentToDrawsDetail") as DrawsModel
        }


        val drawsUserModel: UserDraws by lazy {
            intent?.getParcelableExtra("fromProfileFragmentToDrawsDetail") as UserDraws
        }



        if (intent.getIntExtra("profileFragment", -1) == 1) {
            drawsProfileData = drawsUserModel
            initProfileData(drawsProfileData!!)
            initSlider(drawsUserModel.draw?.galleries!!)

        } else {
            drawsData = drawsModel

            initData(drawsData!!)
            initSlider(drawsData!!.galleries!!)
        }

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

        cvJoinCount.setOnClickListener {
            launchActivity<DrawsUsersActivity> {  }
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

    @SuppressLint("SetTextI18n")
    fun initProfileData(data: UserDraws){

        tvDrawTitle.text = data.draw?.title
        tvDrawSubtitle.text = data.draw?.sub_title
        tvDrawDetailContent.text = data.draw?.content
        tvDrawDetailDateStart.text = data.draw?.last_date
        tvJoinCount.text = "Katılanlar: "+ data.draw?.draw_users_count

    }

}
