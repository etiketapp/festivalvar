package com.example.festivalvar.ui.draws.drawsusersactivity

import android.view.View
import androidx.lifecycle.Observer
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.user.draws.UserDraws
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.ui.home.festivallikes.FestivalLikesAdapter
import com.example.festivalvar.ui.main.MainActivity
import com.mobillium.birebirdiyet.utils.extensions.launchActivity
import kotlinx.android.synthetic.main.activity_draws_users.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrawsUsersActivity : BaseActivity(), IUserDrawsNavigator, UserDrawsClickListener {


    override val layoutId: Int?
        get() = R.layout.activity_draws_users

    private val viewModel by viewModel<DrawsUsersViewModel>()
    private val userDrawsAdapter by lazy { DrawsUsersAdapter(arrayListOf(), this) }


    override fun initNavigator() {
    }

    override fun initUI() {
        viewModel.setNavigator(this)
        ivToolbarLogo.visibility = View.VISIBLE
        iv_back.visibility = View.VISIBLE

        observeViewModel()
        viewModel.getUserDraws(1)
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

    }

    override fun onClick(model: UserDraws) {
        launchActivity<MainActivity> {
            this.putExtra("fromUserDrawsToProfileFragment", model.user?.id)
        }
    }

    fun observeViewModel(){
        viewModel.userDrawsList.observe(this, Observer {
            initDrawsUsers(it)
        })
    }

    fun initDrawsUsers(data: ArrayList<UserDraws>){
        rvUserDrawsList.adapter = userDrawsAdapter
        userDrawsAdapter.add(data)

    }

}
