package com.mobillium.birebirdiyet.utils

import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.festivalvar.CoreApp
import com.example.festivalvar.utils.AppConstants
import com.example.festivalvar.utils.FilePickUtils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PhotoUpload :Activity() {
    /**Fotoğraf seç*/
    var imageFilePath: String? = null
    var filePath: String? = null
    private var myDialog: Dialog? = null
    lateinit var photo: ImageView
    private var isCameraClicked: Boolean = false
    private var CAMERA_PERMISSION: String = android.Manifest.permission.CAMERA
    private var READ_EXTERNAL_STORAGE_PERMISSION: String = android.Manifest.permission.READ_EXTERNAL_STORAGE
    private var WRITE_EXTERNAL_STORAGE_PERMISSION: String = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

/*
    fun showPopupPhoto(imageView: ImageView,context: Context) {

        this.photo = imageView
        myDialog = Dialog(context)
        myDialog?.setContentView(R.layout.custom_popup_gallery)
        val gallery: CircleImageView = myDialog!!.findViewById(R.id.ivGallery) as CircleImageView
        val camera: CircleImageView = myDialog!!.findViewById(R.id.ivCamera) as CircleImageView

        Objects.requireNonNull(myDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)


        gallery.setOnClickListener {

            isCameraClicked = false
            myDialog!!.dismiss()
            checkMultiplePermissions(1, this)
        }

        camera.setOnClickListener {
            isCameraClicked = true
            myDialog!!.dismiss()
            checkMultiplePermissions(1, this)

        }

        myDialog?.show()
        myDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //myDialog?.window!!.decorView.background.colorFilter = LightingColorFilter(-0x1000000, resources.getColor(R.color.black))

    }
*/



    private fun openCamera() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, IMAGE_PICK_CODE)
        }

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat(
            "yyyyMMdd_HHmmss",
            Locale.getDefault()
        ).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
        imageFilePath = image.getAbsolutePath()
        return image
    }

    private fun setResultOk(imagePath: Uri?) {
        val intent = Intent()
        intent.putExtra("path", imagePath)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun getCacheImagePath(fileName: String): Uri {
        val path = File(externalCacheDir, "camera")
        if (!path.exists()) path.mkdirs()
        val image = File(path, fileName)
        return FileProvider.getUriForFile(this, "$packageName.provider", image)
    }

    private fun openGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK
        )
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, IMAGE_PICK_CODE)
    }

    private fun saveImage(bitmap: Bitmap): Uri {
        //Bitmap sourceUri=(Bitmap) data.getExtras().get("data");
        val root = getFilesDir().toString()
        val myDir = File(root)
        myDir.mkdirs()
        val n = System.currentTimeMillis()
        //Give the file name that u want
        val fname: String = n.toString() + "_.jpg"
        val filePath = root + "/" + fname
        val destination = File(myDir, fname)
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(destination)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Uri.parse(filePath)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null && isCameraClicked) {

            val bitmap: Bitmap = data.extras.get("data") as Bitmap
            photo.setImageBitmap(bitmap)
            photo.tag = AppConstants.NOT_NULL
            val uri = saveImage(bitmap)
            setPath(uri.toString())
            multiPath(uri.toString())

        } else if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data?.data
            photo.setImageURI(uri)
            photo.tag = AppConstants.NOT_NULL
            //   photo.scaleType = ImageView.ScaleType.FIT_XY

            val filePath = FilePickUtils.getPath(this, uri!!)
            if (filePath != null) {
                setPath(filePath)
                multiPath(filePath)


            }
        }

    }

    open fun multiPath(filePath: String) {

    }

    fun getImageUri(inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(CoreApp.context.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun setPath(file: String) {
        this.filePath = file
    }

    fun getPath(): String? {
        return filePath
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun checkMultiplePermissions(permissionCode: Int, context: Context) {

        val PERMISSIONS =
            arrayOf<String>(CAMERA_PERMISSION, READ_EXTERNAL_STORAGE_PERMISSION, WRITE_EXTERNAL_STORAGE_PERMISSION)
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions(context as Activity, PERMISSIONS, permissionCode)
        } else {

            if (isCameraClicked) {

                openCamera()

            } else {

                openGallery()
            }
        }
    }

    private fun hasPermissions(context: Context?, permissions: Array<String>): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {

                    if (isCameraClicked) {

                        openCamera()

                    } else {

                        openGallery()
                    }

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    // Toast.makeText(activity!!, "deneme else", Toast.LENGTH_SHORT).show()

                }
                return
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;

    }

}