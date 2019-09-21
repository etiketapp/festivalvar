package com.example.festivalvar.ui.profile

import android.view.View
import androidx.lifecycle.Observer
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.user.commentedfestivals.CommentedFestivalModel
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.example.festivalvar.ui.base.BaseFragment
import com.example.festivalvar.ui.festivaldetail.FestivalDetailActivity
import com.example.festivalvar.ui.home.festivaladapter.AdapterFestival
import com.example.festivalvar.ui.profile.adapters.FestivalCommentAdapter
import com.example.festivalvar.ui.profile.adapters.FestivalDrawsAdapter
import com.example.festivalvar.ui.profile.adapters.FestivalLikeAdapter
import com.example.festivalvar.ui.profile.profilesettings.ProfileSettingsActivity
import com.example.festivalvar.ui.profile.viewholders.FestivalCommentsClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalDrawsClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalLikeClickListener
import com.example.festivalvar.utils.PrefUtils
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(), IProfileNavigator, FestivalLikeClickListener, FestivalCommentsClickListener, FestivalDrawsClickListener {
    override fun onClick(model: UserDraws) {

    }

    override fun onClick(model: CommentedFestivalModel) {

    }

    override fun onClick(model: LikedFestivalsModel) {
        activity!!.launchActivity<FestivalDetailActivity> {
            this.putExtra("fromProfileToFestivalDetail", model)
            this.putExtra("profileFragment", 1)
        }

    }

    override val layoutId: Int
        get() = R.layout.fragment_profile

    private val viewModel by viewModel<ProfileViewModel>()

    private val festivalLikeAdapter by lazy { FestivalLikeAdapter(arrayListOf(), this) }
    private val festivalCommentedAdapter by lazy { FestivalCommentAdapter(arrayListOf(), this) }
    private val userDrawsAdapter by lazy { FestivalDrawsAdapter(arrayListOf(), this) }


    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {

        observeViewModel()

        tvToolbarTitle.visibility = View.VISIBLE
        iv_list.visibility = View.GONE
        tvToolbarTitle.text = PrefUtils.getUser().full_name

        viewModel.getFestivalLike(PrefUtils.getUserId())
        viewModel.getFestivalComment(PrefUtils.getUserId())
        viewModel.getUserDraws(PrefUtils.getUserId())

        linearLikes.visibility = View.VISIBLE

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

                linearLikes.visibility = View.VISIBLE
                linearComments.visibility = View.GONE
                linearDraws.visibility = View.GONE

                //showOnlineUIVisible()
            }
            //Klinik Randevu butonu aktifse
            else if (it == 1) {

                linearLikes.visibility = View.GONE
                linearComments.visibility = View.VISIBLE
                linearDraws.visibility = View.GONE

                //showClinicUIVisible()

            } else if(it == 2){

                linearLikes.visibility = View.GONE
                linearComments.visibility = View.GONE
                linearDraws.visibility = View.VISIBLE

            }
        }

// Get current position
        segmentedButtonGroupProfile.position
    }

    fun initFestivalLiked(data: ArrayList<LikedFestivalsModel>){
        rvFestivalLike.adapter = festivalLikeAdapter
        festivalLikeAdapter.add(data)

    }

    fun initFestivalComments(data: ArrayList<CommentedFestivalModel>){
        rvFestivalComments.adapter = festivalCommentedAdapter
        festivalCommentedAdapter.add(data)
    }

    fun initUSerDraws(data: ArrayList<UserDraws>) {
        rvFestivalDraws.adapter = userDrawsAdapter
        userDrawsAdapter.add(data)
    }

    fun observeViewModel(){
        viewModel.festivalLikeDataList.observe(this, Observer {
            initFestivalLiked(it)

        })

        viewModel.festivalCommentDataList.observe(this, Observer {
            initFestivalComments(it)
        })

        viewModel.userDrawsDataList.observe(this, Observer {
            initUSerDraws(it)
        })
    }

}
