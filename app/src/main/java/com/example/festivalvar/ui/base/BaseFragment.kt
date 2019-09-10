package com.example.festivalvar.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.festivalvar.R
import com.example.festivalvar.utils.customscreen.LoadingDialog
import com.example.festivalvar.utils.extensions.showToast

abstract class BaseFragment : Fragment(), IBaseNavigator {

    @get:LayoutRes
    abstract val layoutId: Int
    private var myDialog: Dialog? = null


    protected abstract fun initNavigator()

    protected abstract fun initUI()

    protected abstract fun initListener()

    private val dialog: AlertDialog by lazy {
        LoadingDialog.Builder().setContext(context)
            .setCancelable(false)
            .setTheme(R.style.LoadingDialogDefault)
            //.setTheme(R.style.SpotsDialog)
            .build()
    }

/*
    fun showPopupCheckIfLogin(){

        myDialog = Dialog(activity)
        myDialog?.setContentView(R.layout.custom_popup_login_register)


        val btnDecline: AppCompatButton = myDialog!!.findViewById(R.id.btnDecline) as AppCompatButton
        val btnAccept: AppCompatButton = myDialog!!.findViewById(R.id.btnAccept) as AppCompatButton

        btnDecline.setOnClickListener {
            myDialog?.hide()
        }

        btnAccept.setOnClickListener {
            activity!!.launchActivity<AuthLoginActivity> {  }
        }

    }
*/


    override fun showLoading() {
        dialog.show()
    }


    override fun hideLoading() {
        dialog.dismiss()
    }


/*
    fun changePhoto() {
        val prefences = PrefUtils.getUser()
        if (prefences.image != null) {
            ivToolbarProfile.load(prefences.image.url.toString())
        }
        if (prefences.image == null) {
            ivToolbarProfile.load(R.drawable.ic_placeholder)
        }
    }
*/



/*
    fun replacePage(fragment: Fragment) {
        var bundle = Bundle()
        bundle.putString(AppConstants.MAIN_FRAGMENT, AppConstants.MAIN_FRAGMENT)
        fragment.arguments = bundle
        fragmentManager!!.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
*/

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

    override fun onError(errorMessage: String, errorCode: Int) {
        showToast(errorMessage + errorCode.toString())
    }

    override fun onSucces(message: String) {
        showToast(message)
    }

    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }

    override fun onStop() {
        super.onStop()
        dialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.dismiss()
    }
}