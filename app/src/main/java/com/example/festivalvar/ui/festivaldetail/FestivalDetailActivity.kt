package com.example.festivalvar.ui.festivaldetail

import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.comments.CommentsActivity
import com.example.festivalvar.utils.extensions.overridePendingTransitionEnter
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.activity_festival_detail.*
import kotlinx.android.synthetic.main.row_messages_item.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FestivalDetailActivity : BaseActivity(), IFestivalDetailNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_festival_detail

    private var viewPager: ViewPager? = null

    var dataSlider: ArrayList<Int> = arrayListOf(R.drawable.bg_header, R.drawable.bg_header)

    private val festivalModel: FestivalModel? = null


    private val viewModel by viewModel<FestivalDetailViewModel>()

    override fun initNavigator() {

        viewModel.setNavigator(this)
    }

    override fun initUI() {
        iv_back.visibility = View.VISIBLE
        //initSlider(dataSlider)
        val festivalModel: FestivalModel by lazy {
            intent?.getParcelableExtra("fromFestivalToDetail") as FestivalModel
        }

        if (festivalModel.image != null) pagerFestivalDetail.load(festivalModel.image?.url!!)
        tvFestivalTitle.text = festivalModel.title
        tvFestivalSubtitle.text = festivalModel.sub_title
        btnFestivalDetail.text = festivalModel.category.title
        val textCost = festivalModel.price
        if (textCost.endsWith(".00")) {

            tvDetailCost.text = (textCost.substring(0, textCost.length - 3) + " TL")
        } else {

            tvDetailCost.text = festivalModel.price
        }
            //tvDetailCost.text = (festivalModel.price + " TL")
            tvFestivalDetailTitle.text = festivalModel.about
            tvFestivalDetailContent.text = festivalModel.advice
            tvFestivalDetailAccomodationContent.text = festivalModel.place
            tvFestivalDetailReccomendationContent.text = festivalModel.advice

        }

        override fun initListener() {
            iv_back.setOnClickListener {
                finish()
            }

            containerComment.setOnClickListener {
                launchActivity<CommentsActivity> { }
                overridePendingTransitionEnter()
            }
        }

        private fun initSlider(data: ArrayList<Int>) {
            //viewPager = view?.findViewById<View>(R.id.pagerFestivalDetail) as ViewPager
            val adapter = SliderViewPagerAdapter(applicationContext, data)
            viewPager!!.adapter = adapter
            indicatorHome?.setViewPager(viewPager!!)


        }


    }
