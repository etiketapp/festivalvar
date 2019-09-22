package com.example.festivalvar.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.FestivalModel
import com.example.festivalvar.data.remote.model.categories.Categories
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
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_home2.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class HomeFragment : BaseFragment(), IHomeNavigator, FestivalClickListener, FestivalMapClickListener,
    OnMapReadyCallback {

    override val layoutId: Int
        get() = R.layout.fragment_home2

    companion object {

        var map: GoogleMap? = null
        var marker: Marker? = null

    }

    var festivalLatLng: ArrayList<LatLng> = arrayListOf()
    var festivalLocations: ArrayList<LocationModel>? = arrayListOf()
    private var festivalModel: ArrayList<FestivalModel> = arrayListOf()
    private var categoryModel: ArrayList<Categories> = arrayListOf()

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
        observeViewModelCategories()
        viewModel.getFestivalList()
        viewModel.getCategoryList()
    }

    override fun initListener() {

        iv_list.setOnClickListener {

            statement = !statement

            if (statement) {

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

    fun observeViewModelFestival() {
        viewModel.festivalDataList.observe(this, Observer {
            initFestivals(it)
            festivalModel = it
        })
    }

    fun observeViewModelCategories() {
        viewModel.categoryDataList.observe(this, Observer {
            categoryModel = it
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
                val latlang =
                    LatLng(festivalLocations!![x].latitude.toDouble(), festivalLocations!![x].longitude.toDouble())
                festivalLatLng.add(latlang)
            }

            festivalLatLng.forEachIndexed { index, it ->

                if(categoryModel[festivalModel[index].category_id!!].title == "Müzik"){

                    Glide.with(context!!).asDrawable()
                        .load(categoryModel[festivalModel[index].category_id!!].image.url!!)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                marker = map?.addMarker(
                                    MarkerOptions().position(it).icon(
                                        BitmapDescriptorFactory.fromBitmap(createCustomMarker(context!!, resource))
                                    )
                                )

                                marker!!.tag = festivalModel[index]


                            }

                        })

                }
                else if(categoryModel[festivalModel[index].category_id!!].title == "Festival") {

                    Glide.with(context!!).asDrawable()
                        .load(categoryModel[festivalModel[index].category_id!!].image.url!!)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                marker = map?.addMarker(
                                    MarkerOptions().position(it).icon(
                                        BitmapDescriptorFactory.fromBitmap(createCustomMarker(context!!, resource))
                                    )
                                )

                                marker!!.tag = festivalModel[index]


                            }

                        })
                }
                else if(categoryModel[festivalModel[index].category_id!!].title == "Yemek"){
                    Glide.with(context!!).asDrawable()
                        .load(categoryModel[festivalModel[index].category_id!!].image.url!!)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                marker = map?.addMarker(
                                    MarkerOptions().position(it).icon(
                                        BitmapDescriptorFactory.fromBitmap(createCustomMarker(context!!, resource))
                                    )
                                )

                                marker!!.tag = festivalModel[index]


                            }

                        })}
                else if(categoryModel[festivalModel[index].category_id!!].title == "Spor"){
                    Glide.with(context!!).asDrawable()
                        .load(categoryModel[festivalModel[index].category_id!!].image.url!!)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                marker = map?.addMarker(
                                    MarkerOptions().position(it).icon(
                                        BitmapDescriptorFactory.fromBitmap(createCustomMarker(context!!, resource))
                                    )
                                )

                                marker!!.tag = festivalModel[index]


                            }

                        })
                }
                else if(categoryModel[festivalModel[index].category_id!!].title == "Diğer"){
                    Glide.with(context!!).asDrawable()
                        .load(categoryModel[festivalModel[index].category_id!!].image.url!!)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                marker = map?.addMarker(
                                    MarkerOptions().position(it).icon(
                                        BitmapDescriptorFactory.fromBitmap(createCustomMarker(context!!, resource))
                                    )
                                )

                                marker!!.tag = festivalModel[index]


                            }

                        })
                }
                else if(categoryModel[festivalModel[index].category_id!!].title == "Teknoloji"){

                    Glide.with(context!!).asDrawable()
                        .load(categoryModel[festivalModel[index].category_id!!].image.url!!)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                marker = map?.addMarker(
                                    MarkerOptions().position(it).icon(
                                        BitmapDescriptorFactory.fromBitmap(createCustomMarker(context!!, resource))
                                    )
                                )
                                marker!!.tag = festivalModel[index]

                            }
                        })
                }
                else {
                    Glide.with(context!!).asDrawable()
                        .load(categoryModel[festivalModel[index].category_id!!].image.url!!)
                        .into(object : CustomTarget<Drawable>() {
                            override fun onLoadCleared(placeholder: Drawable?) {

                            }

                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                marker = map?.addMarker(
                                    MarkerOptions().position(it).icon(
                                        BitmapDescriptorFactory.fromBitmap(createCustomMarker(context!!, resource))
                                    )
                                )
                                marker!!.tag = festivalModel[index]

                            }
                        })

                }

                //marker!!.tag = festivalModel[index]
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

    private fun createCustomMarker(context: Context, image: Drawable): Bitmap {

        val marker = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.bg_marker_icon,
            null
        )
        val markerImage = marker.findViewById(R.id.ivMarkerIcon) as ImageView
        markerImage.setImageDrawable(image)

        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        marker.layoutParams = ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT)
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(marker.measuredWidth, marker.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        marker.draw(canvas)
        return bitmap
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
