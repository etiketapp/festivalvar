package com.example.festivalvar.ui.draws.drawsdetail

import android.annotation.SuppressLint
import android.view.View
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.Galleries
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.draws.drawsusersactivity.DrawsUsersActivity
import com.example.festivalvar.ui.festivaldetail.SliderViewPagerAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.activity_draws_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrawsDetailActivity : BaseActivity(), IDrawsDetailNavigator, RewardedVideoAdListener {

    override val layoutId: Int?
        get() = R.layout.activity_draws_detail

    private val viewModel by viewModel<DrawsDetailViewModel>()

    private var drawsData: DrawsModel? = null
    private var drawsProfileData: UserDraws? = null
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initUI() {

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this
        loadRewardedVideoAd()

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

            if (drawsProfileData!!.draw?.is_joined!!) {
                btnJoin.setBackgroundResource(R.drawable.bg_button_darkgrey)
                btnJoin.text = "KATILDINIZ"

            } else {
                btnJoin.setBackgroundResource(R.drawable.bg_button)
                btnJoin.text = "KATIL"

                btnJoin.setOnClickListener {
                    drawsProfileData!!.draw_id?.let { it1 -> viewModel.getDrawJoin(it1) }
                }
            }

        } else {
            drawsData = drawsModel

            initData(drawsData!!)
            initSlider(drawsData!!.galleries!!)

            if (drawsData!!.is_joined!!) {
                btnJoin.setBackgroundResource(R.drawable.bg_button_darkgrey)
                btnJoin.text = "KATILDINIZ"

            } else {
                btnJoin.setBackgroundResource(R.drawable.bg_button)
                btnJoin.text = "KATIL"

                btnJoin.setOnClickListener {
                    showRewardedVideoAd()
                }
            }
        }

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

        cvJoinCount.setOnClickListener {
            launchActivity<DrawsUsersActivity> { }
        }
    }

    private fun initSlider(data: ArrayList<Galleries>) {
        val adapter = SliderViewPagerAdapter(applicationContext, data)
        pagerDrawsDetail?.adapter = adapter
        indicatorDraw?.setViewPager(pagerDrawsDetail)

    }

    @SuppressLint("SetTextI18n")
    fun initData(data: DrawsModel) {

        tvDrawTitle.text = data.title
        tvDrawSubtitle.text = data.sub_title
        tvDrawDetailContent.text = data.content
        tvDrawDetailDateStart.text = data.last_date
        tvJoinCount.text = "Katılanlar: " + data.draw_users_count

    }

    @SuppressLint("SetTextI18n")
    fun initProfileData(data: UserDraws) {

        tvDrawTitle.text = data.draw?.title
        tvDrawSubtitle.text = data.draw?.sub_title
        tvDrawDetailContent.text = data.draw?.content
        tvDrawDetailDateStart.text = data.draw?.last_date
        tvJoinCount.text = "Katılanlar: " + data.draw?.draw_users_count

    }

    override fun drawJoinSuccess() {
        btnJoin.setBackgroundResource(R.drawable.bg_button_darkgrey)
        btnJoin.text = "KATILDINIZ"
    }

    override fun onRewardedVideoAdClosed() {
        loadRewardedVideoAd()
    }

    override fun onRewardedVideoAdLeftApplication() {
        loadRewardedVideoAd()
    }

    override fun onRewardedVideoAdLoaded() {
    }

    override fun onRewardedVideoAdOpened() {
    }

    override fun onRewardedVideoCompleted() {
        loadRewardedVideoAd()
    }

    override fun onRewarded(p0: RewardItem?) {
        drawsData!!.id?.let { it1 -> viewModel.getDrawJoin(it1) }
    }

    override fun onRewardedVideoStarted() {

    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        loadRewardedVideoAd()
    }

    private fun loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
            AdRequest.Builder().build())
    }

    private fun showRewardedVideoAd() {
        if (mRewardedVideoAd.isLoaded) {
            mRewardedVideoAd.show()
        }
    }

}
