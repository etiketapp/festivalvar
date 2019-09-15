package com.example.festivalvar.ui.draws.drawsdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.FestivalModel.Galleries
import com.mobillium.birebirdiyet.utils.extensions.loadFromUrl

class DrawsSliderViewPagerAdapter(
    private val context: Context,
    private val sliders: ArrayList<Galleries>): PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return sliders.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.row_slider_vp_item, null)
        val image = v.findViewById<View>(R.id.imageView) as ImageView
        image.loadFromUrl(sliders[position].image.url!!)

        val vp = container as ViewPager
        vp.addView(v, 0)
        return v
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}