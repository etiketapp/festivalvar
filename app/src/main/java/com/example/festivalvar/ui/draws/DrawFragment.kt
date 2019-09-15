package com.example.festivalvar.ui.draws

import android.view.View
import androidx.lifecycle.Observer
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.draws.DrawsModel
import com.example.festivalvar.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_draw.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrawFragment : BaseFragment(), IDrawFragmentNavigator, DrawsClickListener {


    override val layoutId: Int
        get() = R.layout.fragment_draw

    private val viewModel by viewModel<DrawFragmentViewModel>()

    private val drawsAdapter by lazy { DrawsAdapter(arrayListOf(), this) }


    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {
        observeViewModel()
        viewModel.getDrawsList()

        ivToolbarLogo.visibility = View.VISIBLE
    }

    override fun initListener() {
    }

    fun observeViewModel(){
        viewModel.drawsDataList.observe(this, Observer {
            initDraws(it)
        })
    }

    fun initDraws(data: ArrayList<DrawsModel>){

        rvDrawList.adapter = drawsAdapter
        drawsAdapter.add(data)
    }

    override fun onClick(model: DrawsModel) {
    }


}
