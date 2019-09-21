package com.example.festivalvar.ui.profile.profilesettings

import android.net.Uri
import android.view.View
import com.bumptech.glide.Glide
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.user.updatepassword.UserUpdatePasswordRequest
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.utils.PrefUtils
import kotlinx.android.synthetic.main.activity_profile_settings.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ProfileSettingsActivity : BaseActivity(), IProfileSettingsNavigator {
    override fun succesChangePassword(message: String) {
    }

    override val layoutId: Int?
        get() = R.layout.activity_profile_settings

    private val viewModel by viewModel<ProfileSettingsViewModel>()

    private var uri: Uri? = null


    override fun initNavigator() {
        viewModel.setNavigator(this)
    }

    override fun initUI() {

        var userData = PrefUtils.getUser()

        iv_back.visibility = View.VISIBLE
        ivToolbarLogo.visibility = View.VISIBLE

        etFullnameProfileSettings.setText(userData.full_name)
        etEmaildProfileSettings1.setText(userData.email)

        if(userData.image != null){
            Glide.with(this).load(userData.image!!.url).into(ivProfileSettingsProfile)
        }

    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }

        btnProfileSettingsSave.setOnClickListener {
            changeInfo()
        }

        btnLogout.setOnClickListener {
            viewModel.getLogout()
        }

        btnProfileSettingsChange.setOnClickListener {
            validateEditText()
        }
    }


    private fun changeInfo() {
        if (uri != null) {
            val file = File(uri?.path)
            val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
            val multipartBody = MultipartBody.Part.createFormData("image", file.name, fileReqBody)
            change(multipartBody)
        } else
            changeNotPhoto()

    }
    private fun change(multipartBody: MultipartBody.Part?) {
        val method: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "PUT")
        val etFullname: RequestBody = RequestBody.create(MediaType.parse("text/plain"), etFullnameProfileSettings.text.toString())
        val etEmail: RequestBody = RequestBody.create(MediaType.parse("text/plain"), etEmaildProfileSettings1.text.toString())

        viewModel.updateUser(method, multipartBody!!, etFullname, etEmail)
    }
    private fun changeNotPhoto() {
        val method: RequestBody = RequestBody.create(MediaType.parse("text/plain"), "PUT")
        val etFullname: RequestBody = RequestBody.create(MediaType.parse("text/plain"), etFullnameProfileSettings.text.toString())
        val etEmail: RequestBody = RequestBody.create(MediaType.parse("text/plain"), etEmaildProfileSettings1.text.toString())

        viewModel.updateUser(method, null, etFullname, etEmail)

    }


    private fun validateEditText() {
        val etPasswordOld = etPasswordProfileSettings1.text.toString()
        val etPasswordNew = etPasswordProfileSettings1New.text.toString()
        val etPasswordNewAgain = etPasswordAgainProfileSettings1.text.toString()

        val request = UserUpdatePasswordRequest(
            etPasswordOld,
            etPasswordNew,
            etPasswordNewAgain
        )
        viewModel.updatePassword(request, PrefUtils.getUserId())


    }

}
