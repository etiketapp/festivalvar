package com.example.festivalvar.ui.home.festivallikes

import android.view.View
import androidx.lifecycle.Observer
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.comments.CommentsModel
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikes
import com.example.festivalvar.data.remote.model.festivallikes.FestivalLikesModel
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.comments.CommentsAdapter
import kotlinx.android.synthetic.main.activity_festival_comments.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FestivalLikesActivity : BaseActivity(), IFestivalLikesNavigator, FestivalLikesClickListener {
    override fun onClick(model: FestivalLikes) {
    }

    override val layoutId: Int?
        get() = R.layout.activity_festival_comments

    private val viewModel by viewModel<FestivalLikesViewModel>()


    private val likesAdapter by lazy { FestivalLikesAdapter(arrayListOf(), this) }

    private var likesData = arrayListOf<FestivalLikes>()

    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        iv_back.visibility = View.VISIBLE
        ivToolbarLogo.visibility = View.VISIBLE

        observeViewModel()
        viewModel.getFestivalLikes(2)
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }
    }

    fun observeViewModel(){
        viewModel.festivalLikedList.observe(this, Observer {
            likesData = it.likes
            initData(likesData)

        })
    }

    fun initData(data: ArrayList<FestivalLikes>){
        rvLikesList.adapter = likesAdapter
        likesAdapter.add(data)


    }

}
