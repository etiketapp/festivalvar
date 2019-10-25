package com.example.festivalvar.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.example.festivalvar.data.remote.model.user.commentedfestivals.CommentedFestivalModel
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.data.remote.model.user.likedfestivals.LikedFestivalsModel
import com.example.festivalvar.ui.base.BaseFragment
import com.example.festivalvar.ui.draws.drawsdetail.DrawsDetailActivity
import com.example.festivalvar.ui.festivaldetail.FestivalDetailActivity
import com.example.festivalvar.ui.profile.adapters.FestivalCommentAdapter
import com.example.festivalvar.ui.profile.adapters.FestivalDrawsAdapter
import com.example.festivalvar.ui.profile.adapters.FestivalLikeAdapter
import com.example.festivalvar.ui.profile.profilesettings.ProfileSettingsActivity
import com.example.festivalvar.ui.profile.viewholders.FestivalCommentsClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalDrawsClickListener
import com.example.festivalvar.ui.profile.viewholders.FestivalLikeClickListener
import com.example.festivalvar.utils.PrefUtils
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class ProfileFragment : BaseFragment(), IProfileNavigator, FestivalLikeClickListener, FestivalCommentsClickListener,
    FestivalDrawsClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_profile

    private val viewModel by viewModel<ProfileViewModel>()

    private val festivalLikeAdapter by lazy { FestivalLikeAdapter(arrayListOf(), this) }
    private val festivalCommentedAdapter by lazy { FestivalCommentAdapter(arrayListOf(), this) }
    private val userDrawsAdapter by lazy { FestivalDrawsAdapter(arrayListOf(), this) }
    private var festivalProfileLikesData = arrayListOf<FestivalLikes>()

    val REQUEST_IMAGE = 100

    private var uri: Uri? = null
    lateinit var image: ImageView



    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {

        var userId = activity!!.intent.getIntExtra("fromFestivalLikesToProfileActivityData", 0)
        var userIdComment = activity!!.intent.getIntExtra("fromCommentsActivityToProfileFragment", 0)
        var userIdDraw = activity!!.intent.getIntExtra("fromUserDrawsToProfileFragment", 0)

        observeViewModel()

        ivToolbarLogo.visibility = View.VISIBLE
        iv_list.visibility = View.GONE


        if (activity!!.intent.hasExtra("fromFestivalLikesToProfileActivityData")) {
            viewModel.getProfileUserDraws(userId)
            viewModel.getProfileFestivalLike(userId)
            viewModel.getProfileFestivalComment(userId)
        } else {

            viewModel.getFestivalComment(PrefUtils.getUserId())
            viewModel.getFestivalLike(PrefUtils.getUserId())
            viewModel.getUserDraws(PrefUtils.getUserId())
        }

        if (activity!!.intent.hasExtra("fromCommentsActivityToProfileFragment")) {

            viewModel.getProfileUserDraws(userIdComment)
            viewModel.getProfileFestivalLike(userIdComment)
            viewModel.getProfileFestivalComment(userIdComment)
        } else {
            viewModel.getFestivalComment(PrefUtils.getUserId())
            viewModel.getFestivalLike(PrefUtils.getUserId())
            viewModel.getUserDraws(PrefUtils.getUserId())


        }

        if (activity!!.intent.hasExtra("fromUserDrawsToProfileFragment")) {
            viewModel.getProfileUserDraws(userIdDraw)
            viewModel.getProfileFestivalLike(userIdDraw)
            viewModel.getProfileFestivalComment(userIdDraw)

        } else {

            viewModel.getFestivalComment(PrefUtils.getUserId())
            viewModel.getFestivalLike(PrefUtils.getUserId())
            viewModel.getUserDraws(PrefUtils.getUserId())
        }

        PrefUtils.getUser().image?.url?.let { ivProfile.load(it) }

        linearLikes.visibility = View.VISIBLE

    }

    override fun initListener() {
        handleSegmentedButton()

        ivSettings.setOnClickListener {
            context!!.launchActivity<ProfileSettingsActivity> { }
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

            } else if (it == 2) {

                linearLikes.visibility = View.GONE
                linearComments.visibility = View.GONE
                linearDraws.visibility = View.VISIBLE

            }
        }

// Get current position
        segmentedButtonGroupProfile.position
    }

    fun initFestivalLiked(data: ArrayList<LikedFestivalsModel>) {
        rvFestivalLike.adapter = festivalLikeAdapter
        festivalLikeAdapter.add(data)

    }

    fun initFestivalComments(data: ArrayList<CommentedFestivalModel>) {
        rvFestivalComments.adapter = festivalCommentedAdapter
        festivalCommentedAdapter.add(data)
    }

    fun initUSerDraws(data: ArrayList<UserDraws>) {
        rvFestivalDraws.adapter = userDrawsAdapter
        userDrawsAdapter.add(data)
    }

    fun observeViewModel() {
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

    override fun onClick(model: UserDraws) {
        activity!!.launchActivity<DrawsDetailActivity> {
            this.putExtra("fromProfileFragmentToDrawsDetail", model)
            this.putExtra("profileFragment", 1)
        }

    }


    override fun onClick(model: CommentedFestivalModel) {
        activity!!.launchActivity<FestivalDetailActivity> {
            this.putExtra("fromProfileToFestivalDetail", model)
            this.putExtra("profileFragment", 2)
        }

    }

    override fun onClick(model: LikedFestivalsModel) {
        activity!!.launchActivity<FestivalDetailActivity> {
            this.putExtra("fromProfileToFestivalDetail", model)
            this.putExtra("profileFragment", 1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE && data != null) {
            if (resultCode == Activity.RESULT_OK) {
                uri = data.getParcelableExtra<Uri>("path")
                try {
                    // You can update this bitmap to your server
                    //val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                    // loading profile image from local cache
                    image.load(uri.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }


    }

}
