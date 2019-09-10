package com.example.festivalvar.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.example.festivalvar.R
import dmax.dialog.SpotsDialog

abstract class BaseDialogFragment : DialogFragment(), IBaseNavigator {
    @get:LayoutRes
    abstract val layoutId: Int

    protected abstract fun initNavigator()

    protected abstract fun initUI()

    protected abstract fun initListener()

    private val dialog: AlertDialog by lazy {
        SpotsDialog.Builder().setContext(context)
            .setCancelable(false)
            .setTheme(R.style.SpotsDialog)
            .build()
    }

    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigator()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListener()
    }

}