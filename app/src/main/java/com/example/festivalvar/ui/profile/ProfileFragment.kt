package com.example.festivalvar.ui.profile

import android.view.View
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.example.festivalvar.R
import com.example.festivalvar.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(), IProfileNavigator {
    override val layoutId: Int
        get() = R.layout.fragment_profile

    private val viewModel by viewModel<ProfileViewModel>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
    }

    override fun initListener() {
        handleSegmentedButton()
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
