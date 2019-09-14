package com.example.festivalvar.ui.profile

import android.view.View
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.example.festivalvar.R
import com.example.festivalvar.ui.base.BaseFragment
import com.example.festivalvar.ui.profile.profilesettings.ProfileSettingsActivity
import com.example.festivalvar.utils.PrefUtils
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(), IProfileNavigator {
    override val layoutId: Int
        get() = R.layout.fragment_profile

    private val viewModel by viewModel<ProfileViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {

        tvToolbarTitle.visibility = View.VISIBLE
        iv_list.visibility = View.GONE
        tvToolbarTitle.text = PrefUtils.getUser().full_name
    }

    override fun initListener() {
        handleSegmentedButton()

        ivSettings.setOnClickListener {
            context!!.launchActivity<ProfileSettingsActivity> {  }
        }
    }

    private fun handleSegmentedButton() {

        segmentedButtonGroupProfile.onPositionChangedListener = SegmentedButtonGroup.OnPositionChangedListener {
            // Handle stuff here
            //Online Randevu butonu aktifse
            if (it == 0) {

                linear.visibility = View.VISIBLE

                //showOnlineUIVisible()
            }
            //Klinik Randevu butonu aktifse
            else if (it == 1) {

                linear.visibility = View.GONE

                //showClinicUIVisible()

            }
        }

// Get current position
        segmentedButtonGroupProfile.position
    }


}
