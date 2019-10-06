package com.example.festivalvar.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.draws.DrawFragment
import com.example.festivalvar.ui.home.HomeFragment
import com.example.festivalvar.ui.messages.MessagesFragment
import com.example.festivalvar.ui.notifications.NotificationsFragment
import com.example.festivalvar.ui.profile.ProfileFragment
import com.example.festivalvar.utils.AppConstants.MAIN_TAB_VALUE
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), IMainNavigator {
    override val layoutId: Int?
        get() = R.layout.activity_main
    private val viewModel by viewModel<MainViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initUI() {
        replaceFragment(HomeFragment())
        navigationDirects()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initListener() {

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            var selectedFragment: Fragment? = null


            when (menuItem.itemId) {
                R.id.nav_home -> {
                    MAIN_TAB_VALUE = "HOME"
                    selectedFragment = HomeFragment()

                }

                R.id.nav_draws -> {
                    MAIN_TAB_VALUE = "DRAWS"
                    selectedFragment = DrawFragment()
                }
                R.id.nav_message -> {
                    MAIN_TAB_VALUE = "MESSAGES"
                    selectedFragment = MessagesFragment()
/*
                    iv_list.visibility = View.GONE
                    iv_filter.visibility = View.GONE
                    ivToolbarLogo.visibility = View.GONE
                    tvToolbarTitle.visibility = View.VISIBLE
                    tvToolbarTitle.text = "Mesajlar"*/

                }

                R.id.nav_notifications -> {
                    MAIN_TAB_VALUE = "NOTIFICATIONS"
                    selectedFragment = NotificationsFragment()
                }
                R.id.nav_profile -> {
                    MAIN_TAB_VALUE = "PROFILE"
                    selectedFragment = ProfileFragment()
/*
                    iv_list.visibility = View.GONE
                    iv_filter.visibility = View.GONE
                    ivToolbarLogo.visibility = View.GONE
                    tvToolbarTitle.visibility = View.VISIBLE
                    tvToolbarTitle.text = "Bade Soyar"*/
                }

            }


            if (selectedFragment != null) {
                replaceFragment(selectedFragment)
            }
            true

        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    fun navigationDirects() {

        if (intent != null) {
            /** FestivalLikes'tan Profile gidiyorsa **/
            if (intent.hasExtra("fromFestivalLikesToProfileActivity")) {

                val userId = intent.getIntExtra("fromFestivalLikesToProfileActivityData", 0)
                intent.putExtra("fromFestivalLikesToProfileActivityData", userId)

                bottom_navigation.selectedItemId = R.id.nav_profile
                replaceFragment(ProfileFragment())

                /**FesivalComments'tan Profile gidiyorsa**/
            } else if (intent.hasExtra("fromCommentsActivityToProfileFragment")) {

                val userId = intent.getIntExtra("fromCommentsActivityToProfileFragment", 0)
                intent.putExtra("fromCommentsActivityToProfileFragment", userId)


                bottom_navigation.selectedItemId = R.id.nav_profile
                replaceFragment(ProfileFragment())


            } else if(intent.hasExtra("fromUserDrawsToProfileFragment")){

                val userId = intent.getIntExtra("fromUserDrawsToProfileFragment", 0)
                intent.putExtra("fromUserDrawsToProfileFragment", userId)


                bottom_navigation.selectedItemId = R.id.nav_profile
                replaceFragment(ProfileFragment())
            }
        }
    }

}
