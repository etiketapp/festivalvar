package com.example.festivalvar.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.location.LocationModel
import com.example.festivalvar.ui.base.BaseFragment
import com.example.festivalvar.ui.festivaldetail.FestivalDetailActivity
import com.example.festivalvar.ui.home.festivaladapter.AdapterFestival
import com.example.festivalvar.ui.home.festivaladapter.FestivalMapAdapter
import com.example.festivalvar.ui.home.festivalviewholder.FestivalClickListener
import com.example.festivalvar.ui.home.festivalviewholder.FestivalMapClickListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.fragment_home2.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class HomeFragment : BaseFragment(), IHomeNavigator, FestivalClickListener, FestivalMapClickListener, OnMapReadyCallback {

    override val layoutId: Int
        get() = R.layout.fragment_home2

    companion object {

        var map: GoogleMap? = null
        var marker: Marker? = null

    }

    var festivalLatLng: ArrayList<LatLng> = arrayListOf()
    var festivalLocations: ArrayList<LocationModel>? = arrayListOf()
    private var festivalModel: ArrayList<FestivalModel> = arrayListOf()

    var statement: Boolean = false


    private val viewModel by viewModel<HomeViewModel>()

    private val festivalAdapter by lazy { AdapterFestival(arrayListOf(), this) }
    private val festivalMapAdapter by lazy { FestivalMapAdapter(arrayListOf(), this) }


    override fun initNavigator() {
        viewModel.setNavigator(this)

    }

    override fun initUI() {


        iv_list.visibility = View.VISIBLE
        iv_filter.visibility = View.VISIBLE
        ivToolbarLogo.visibility = View.VISIBLE
        tvToolbarTitle.visibility = View.GONE

        linearFestivalContainer.visibility = View.VISIBLE
        clServiceSearch.visibility = View.VISIBLE

        setMap()
        mapScrollable()
        observeViewModelFestival()
        viewModel.getFestivalList()

    }

    override fun initListener() {

        iv_list.setOnClickListener {

            statement = !statement

            if(statement) {

                rlMap.visibility = View.VISIBLE
                linearFestivalContainer.visibility = View.GONE
                clServiceSearch.visibility = View.GONE

                iv_list.setImageDrawable(resources.getDrawable(R.drawable.ic_nav_map))

            } else {

                rlMap.visibility = View.GONE
                linearFestivalContainer.visibility = View.VISIBLE
                clServiceSearch.visibility = View.VISIBLE

                iv_list.setImageDrawable(resources.getDrawable(R.drawable.ic_nav_list))
            }

        }

    }

    fun observeViewModelFestival(){
        viewModel.festivalDataList.observe(this, Observer {
            initFestivals(it)
            festivalModel = it
        })
    }

    private fun initFestivals(data: ArrayList<FestivalModel>) {

        rvFestivalList.adapter = festivalAdapter
        festivalAdapter.add(data)

        rvFestivalMapList.adapter = festivalMapAdapter
        festivalMapAdapter.add(data)

        for (x in 0 until (data.size)) {
            festivalLocations?.add(data[x].location)
            festivalModel.add(data[x])
        }
    }


    override fun onClick(model: FestivalModel) {
        context!!.launchActivity<FestivalDetailActivity> {
            this.putExtra("fromFestivalToDetail", model)
        }

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {

        map = googleMap

        map?.setOnMapLoadedCallback {

            for (x in 0 until (festivalLocations!!.size)) {
                val latlang = LatLng(festivalLocations!![x].latitude.toDouble(), festivalLocations!![x].longitude.toDouble())
                festivalLatLng.add(latlang)
            }

            festivalLatLng.forEachIndexed { index, it ->
                marker = map?.addMarker(
                    MarkerOptions().position(it).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title("Festival ${index}")
                )
                marker!!.tag = festivalModel[index]
            }


            val position = CameraPosition.Builder()
                .target(
                    LatLng(39.0, 32.0)
                )
                .zoom(4.0F)
                .tilt(6.0F)
                .build()

            map!!.animateCamera(CameraUpdateFactory.newCameraPosition(position))

        }
    }


    private fun setMap() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapFestival) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun mapScrollable() {
        //make scroll easily

        transparent_image.setOnTouchListener(View.OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    // Disallow ScrollView to intercept touch events.
                    svFestival.requestDisallowInterceptTouchEvent(true)
                    // Disable touch on transparent view
                    false
                }

                MotionEvent.ACTION_UP -> {
                    // Allow ScrollView to intercept touch events.
                    svFestival.requestDisallowInterceptTouchEvent(false)
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    svFestival.requestDisallowInterceptTouchEvent(true)
                    false
                }
                else -> true
            }
        })
    }

}
