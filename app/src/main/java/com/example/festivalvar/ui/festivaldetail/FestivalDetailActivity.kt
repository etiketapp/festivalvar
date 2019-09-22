package com.example.festivalvar.ui.festivaldetail

import android.view.MotionEvent
import android.view.View
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.FestivalModel.Galleries
import com.example.festivalvar.data.remote.model.user.commentedfestivals.CommentedFestivalModel
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.comments.CommentsActivity
import com.example.festivalvar.utils.extensions.overridePendingTransitionEnter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.activity_festival_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FestivalDetailActivity : BaseActivity(), IFestivalDetailNavigator, OnMapReadyCallback {

    override val layoutId: Int?
        get() = R.layout.activity_festival_detail

    private lateinit var map: GoogleMap

    private var festivalLongitute: Double = 0.0
    private var festivalLatitute: Double = 0.0
    private var marker: Marker? = null
    var festivalLatLng: ArrayList<LatLng> = arrayListOf()

    //private val festivalModel: FestivalModel? = null


    private val viewModel by viewModel<FestivalDetailViewModel>()

    var festivalData: FestivalModel? = null


    val festivalLikedModel: LikedFestivalsModel by lazy {
        intent?.getParcelableExtra("fromProfileToFestivalDetail") as LikedFestivalsModel
    }


    val festivalCommentedModel: CommentedFestivalModel by lazy {
        intent?.getParcelableExtra("fromProfileToFestivalDetail") as CommentedFestivalModel
    }

    override fun initNavigator() {

        viewModel.setNavigator(this)
    }

    override fun initUI() {
        iv_back.visibility = View.VISIBLE
        ivToolbarLogo.visibility = View.VISIBLE
        //initSlider(dataSlider)

        setMap()
        mapScrollable()

        /** Likelanan festivallere bakmak için **/
        if (intent.getIntExtra("profileFragment", -1) == 1) {

            tvFestivalSubtitle.text = festivalLikedModel.festival?.sub_title
            tvFestivalDetailContent.text = festivalLikedModel.festival?.about
            tvFestivalDetailDateStart.text = festivalLikedModel.festival?.start_date
            tvFestivalDetailDateEnd.text = festivalLikedModel.festival?.end_date
            val textCost = festivalLikedModel.festival?.price
            tvDetailLikeCount.text = festivalLikedModel.festival?.likes_count.toString()
            tvDetailCommentCount.text = festivalLikedModel.festival?.comments_count.toString()

            if (textCost!!.endsWith(".00")) {

                tvDetailCost.text = (textCost!!.substring(0, textCost.length - 3) + " TL")
            } else {

                tvDetailCost.text = festivalLikedModel.festival?.price
            }
            tvFestivalDetailReccomendationContent.text = festivalLikedModel.festival?.advice
            festivalLikedModel.festival?.galleries?.let { initSlider(it) }

            /** Yorum atılan festivallere bakmak için **/
        } else if(intent.getIntExtra("profileFragment", -1) == 2){

            tvFestivalSubtitle.text = festivalCommentedModel.festival?.sub_title
            tvFestivalDetailContent.text = festivalCommentedModel.festival?.about
            tvFestivalDetailDateStart.text = festivalCommentedModel.festival?.start_date
            tvFestivalDetailDateEnd.text = festivalCommentedModel.festival?.end_date
            val textCost = festivalCommentedModel.festival?.price
            tvDetailLikeCount.text = festivalCommentedModel.festival?.likes_count.toString()
            tvDetailCommentCount.text = festivalCommentedModel.festival?.comments_count.toString()

            if (textCost!!.endsWith(".00")) {

                tvDetailCost.text = (textCost!!.substring(0, textCost.length - 3) + " TL")
            } else {

                tvDetailCost.text = festivalCommentedModel.festival?.price
            }
            tvFestivalDetailReccomendationContent.text = festivalCommentedModel.festival?.advice
            festivalCommentedModel.festival?.galleries?.let { initSlider(it) }
        } else {
            val festivalModel: FestivalModel by lazy {
                intent?.getParcelableExtra("fromFestivalToDetail") as FestivalModel
            }

            festivalData = festivalModel


            festivalModel.galleries?.let { initSlider(it) }

            tvFestivalSubtitle.text = festivalModel.sub_title
            tvFestivalDetailContent.text = festivalModel.about
            tvFestivalDetailDateStart.text = festivalModel.start_date
            tvFestivalDetailDateEnd.text = festivalModel.end_date
            btnFestivalDetail.text = festivalModel.category?.title
            val textCost = festivalModel.price
            tvDetailLikeCount.text = festivalModel.likes_count.toString()
            tvDetailCommentCount.text = festivalModel.comments_count.toString()
            if (textCost.endsWith(".00")) {

                tvDetailCost.text = (textCost.substring(0, textCost.length - 3) + " TL")
            } else {

                tvDetailCost.text = festivalModel.price
            }
            tvFestivalDetailReccomendationContent.text = festivalModel.advice
        }

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

        containerComment.setOnClickListener {
            launchActivity<CommentsActivity> {

            }
            overridePendingTransitionEnter()
        }

    }

    private fun initSlider(data: ArrayList<Galleries>) {
        val adapter = SliderViewPagerAdapter(applicationContext, data)
        pagerFestivalDetail?.adapter = adapter
        indicatorHome?.setViewPager(pagerFestivalDetail)

    }


    private fun setFestivalLocation() {

        /** Likelanan festivallerin lokasyonu**/
        if(intent.getIntExtra("profileFragment", -1) == 1){

            festivalLatitute = festivalLikedModel.festival?.location?.latitude!!.toDouble()
            festivalLongitute = festivalLikedModel.festival?.location?.longitude!!.toDouble()

            /** YOrumlanan festivallerin lokasyonu**/
        } else if(intent.getIntExtra("profileFragment", -1) == 2){

            festivalLatitute = festivalCommentedModel.festival?.location?.latitude!!.toDouble()
            festivalLongitute = festivalCommentedModel.festival?.location?.longitude!!.toDouble()

        } else {

            festivalLatitute = festivalData?.location?.latitude!!.toDouble()
            festivalLongitute = festivalData?.location?.longitude!!.toDouble()
        }
    }

    private fun mapScrollable() {
        //make scroll easily on map

        transparent_festival_detail_image.setOnTouchListener(View.OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    // Disallow ScrollView to intercept touch events.
                    svFestivalDetail.requestDisallowInterceptTouchEvent(true)
                    // Disable touch on transparent view
                    false
                }

                MotionEvent.ACTION_UP -> {
                    // Allow ScrollView to intercept touch events.
                    svFestivalDetail.requestDisallowInterceptTouchEvent(false)
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    svFestivalDetail.requestDisallowInterceptTouchEvent(true)
                    false
                }

                else -> true
            }
        })

    }

    private fun setMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFestivalDetail) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        map.setOnMapLoadedCallback {

            setFestivalLocation()
            val fesitvalLocation =
                LatLng(festivalLatitute, festivalLongitute)
            festivalLatLng.add(fesitvalLocation)


            festivalLatLng.forEach {
                marker = map.addMarker(
                    MarkerOptions().position(it).icon(
                        BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_AZURE
                        )
                    )
                )
            }

            val position = CameraPosition.Builder()
                .target(fesitvalLocation)
                .zoom(5.0F)
                .tilt(6.0F)
                .build()

            map.animateCamera(CameraUpdateFactory.newCameraPosition(position))


        }
    }


}
