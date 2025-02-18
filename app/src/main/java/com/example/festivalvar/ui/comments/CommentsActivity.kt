package com.example.festivalvar.ui.comments

import android.view.View
import androidx.lifecycle.Observer
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.comments.CommentsModel
import com.example.festivalvar.data.remote.model.comments.FestivalCommentsUser
import com.example.festivalvar.data.remote.model.comments.PostCommentModelRequest
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.draws.DrawsAdapter
import com.example.festivalvar.ui.main.MainActivity
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsActivity : BaseActivity(), ICommentsNavigator, CommentsClickListener {


    override val layoutId: Int?
        get() = R.layout.activity_comments

    private val viewModel by viewModel<CommentsViewModel>()

    private val commentsAdapter by lazy { CommentsAdapter(arrayListOf(), this) }

    private var commentsData = arrayListOf<CommentsModel>()


    override fun initNavigator() {
        viewModel.setNavigator(this)
        viewModel.getFestivalComments(1)
    }

    override fun initUI() {
        observeViewModel()
        tvToolbarTitle.visibility = View.GONE
        ivToolbarLogo.visibility = View.VISIBLE
        iv_back.visibility = View.VISIBLE

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

        icSend.setOnClickListener {
            val etWriteComment = etWriteComment.text.toString()
            val request = PostCommentModelRequest(1, etWriteComment)
            viewModel.postComment(request)

        }
    }

    fun observeViewModel(){
        viewModel.festivalCommentList.observe(this, Observer {
            commentsData = it.comments!!
            initComments(it.comments)
        })
    }

    fun initComments(data: ArrayList<CommentsModel>){
        rvCommentList.adapter = commentsAdapter
        commentsAdapter.add(data)
    }

    override fun onClick(model: CommentsModel) {
        launchActivity<MainActivity> {
            this.putExtra("fromCommentsActivityToProfileFragment", model.user_id)
        }

    }

}
