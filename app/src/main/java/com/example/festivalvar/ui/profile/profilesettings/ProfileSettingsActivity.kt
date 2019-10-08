package com.example.festivalvar.ui.profile.profilesettings

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.festivalvar.R
import com.example.festivalvar.data.remote.model.user.updatepassword.UserUpdatePasswordRequest
import com.example.festivalvar.ui.base.BaseActivity
import com.example.festivalvar.utils.PrefUtils
import com.example.festivalvar.utils.extensions.ImagePickerActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mobillium.birebirdiyet.utils.extensions.load
import kotlinx.android.synthetic.main.activity_profile_settings.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException

class ProfileSettingsActivity : BaseActivity(), IProfileSettingsNavigator {
    override fun succesChangePassword(message: String) {
    }

    override val layoutId: Int?
        get() = R.layout.activity_profile_settings

    private val viewModel by viewModel<ProfileSettingsViewModel>()

    private var uri: Uri? = null
    lateinit var image: ImageView

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

        ivPhoto.setOnClickListener {
            openCameraOrGalery(ivProfileSettingsProfile)
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


    private fun openCameraOrGalery(image: ImageView) {
        this.image = image
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        showImagePickerOptions()
                    } else {
                        if (report.isAnyPermissionPermanentlyDenied)
                            showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    private fun showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptionsPopup(this, object : ImagePickerActivity.PickerOptionListener {
            override fun onTakeCameraSelected() {
                launchCameraIntent()
            }

            override fun onChooseGallerySelected() {
                launchGalleryIntent()
            }
        })
    }

    private fun launchCameraIntent() {
        val intent = Intent(this@ProfileSettingsActivity, ImagePickerActivity::class.java)
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE)

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000)
        //intent.putExtra(ImagePickerActivity.TITLE, resources.getString(R.string.crop_title_profil))

        startActivityForResult(intent, REQUEST_IMAGE)
    }

    private fun launchGalleryIntent() {
        val intent = Intent(this@ProfileSettingsActivity, ImagePickerActivity::class.java)
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE)

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
        //intent.putExtra(ImagePickerActivity.TITLE, resources.getString(R.string.crop_title_profil))

        startActivityForResult(intent, REQUEST_IMAGE)
    }

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        //builder.setTitle(getString(R.string.dialog_permission_title))
        //builder.setMessage(getString(R.string.dialog_permission_message))
        builder.setPositiveButton("Ayarlara git") { dialog, which ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(getString(android.R.string.cancel)) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE && data != null) {
            if (resultCode == Activity.RESULT_OK) {
                uri = data.getParcelableExtra<Uri>("path")
                try {
                    // You can update this bitmap to your server
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                    // loading profile image from local cache
                    image.load(uri.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

}
