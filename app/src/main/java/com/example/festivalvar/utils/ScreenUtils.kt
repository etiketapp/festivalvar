package com.example.festivalvar.utils

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import com.example.festivalvar.CoreApp

object ScreenUtils {

    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }

    fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun convertDpiToPixel(dpi: Int): Int {
        var pixel = 0f
        try {
            val r = CoreApp.context.getResources()
            pixel = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpi.toFloat(),
                r.getDisplayMetrics()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return pixel.toInt()
    }

    fun isTablet(context: Context): Boolean {
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

}