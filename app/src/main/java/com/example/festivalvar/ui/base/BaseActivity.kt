package com.example.festivalvar.ui.base


import android.annotation.TargetApi
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.festivalvar.R
import com.example.festivalvar.utils.FilePickUtils
import com.example.festivalvar.utils.customscreen.LoadingDialog
import com.example.festivalvar.utils.extensions.showToast
import com.example.festivalvar.utils.helper.InputImageCompressor
import java.io.File
import java.io.FileOutputStream
import java.util.*


abstract class BaseActivity : AppCompatActivity(), IBaseNavigator {
    @get:LayoutRes
    abstract val layoutId: Int?
    private var myDialog: Dialog? = null
    private var isCameraClicked: Boolean = false
    private var CAMERA_PERMISSION: String = android.Manifest.permission.CAMERA
    private var READ_EXTERNAL_STORAGE_PERMISSION: String = android.Manifest.permission.READ_EXTERNAL_STORAGE
    private var WRITE_EXTERNAL_STORAGE_PERMISSION: String = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

    lateinit var photo: ImageView
    var filePath: String? = null
    var filem: Uri? = null
    var cityValue: String? = ""
    var birthDateValue: String? = ""
    var photoFile: File? = null
    var imageFilePath: String? = null


    protected abstract fun initNavigator()

    protected abstract fun initUI()

    protected abstract fun initListener()

    private val dialog: AlertDialog by lazy {
        LoadingDialog.Builder().setContext(this)
            .setCancelable(false)
            .setTheme(R.style.LoadingDialogDefault)
            //.setTheme(R.style.SpotsDialog)
            .build()
    }


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentView(it) }
        initNavigator()
        initUI()
        initListener()
        Log.d("LIFECYCLE", "onCreate")
    }

/*
    fun showPopupDate(textTime: TextView, formControlİnterface: formControlİnterface) {

        var adapter: DateAdapter? = null
        var dateList = ArrayList<Date>()

        val c = Calendar.getInstance()
        var day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek = sdf.format(d)

        myDialog = Dialog(this)
        myDialog?.setContentView(R.layout.custom_date)
        val gridView: GridView = myDialog?.findViewById(R.id.gridview) as GridView
        val back: ImageView = myDialog!!.findViewById(R.id.back) as ImageView
        val next: ImageView = myDialog!!.findViewById(R.id.next) as ImageView
        val date: TextView = myDialog!!.findViewById(R.id.date) as TextView

        date.setText("$day.$month.$year $dayOfTheWeek")

        back.setOnClickListener {
            c.add(Calendar.DAY_OF_MONTH, -1)
            var day = c.get(Calendar.DAY_OF_MONTH)
            date.setText("$day.$month.$year $dayOfTheWeek")
        }
        next.setOnClickListener {
            c.add(Calendar.DAY_OF_MONTH, 1)
            var day = c.get(Calendar.DAY_OF_MONTH)
            date.setText("$day.$month.$year $dayOfTheWeek")
        }
        date.setOnClickListener {
        }

        dateList.add(Date("8:45"))
        dateList.add(Date("09:30"))
        dateList.add(Date("10:15"))
        dateList.add(Date("11:00"))
        dateList.add(Date("11:45"))
        dateList.add(Date("12:30"))
        dateList.add(Date("13:15"))
        dateList.add(Date("14:00"))
        dateList.add(Date("14:45"))
        dateList.add(Date("15:30"))
        dateList.add(Date("16:15"))
        dateList.add(Date("17:00"))

        adapter = DateAdapter(this, dateList, myDialog!!, textTime, formControlİnterface)
        gridView.adapter = adapter

        myDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog!!.window.setGravity(Gravity.BOTTOM)
        myDialog!!.show()
    }
*/

/*
    fun chooseCity(view: View, cityText: TextView) {
        var city = PrefUtils.getCity()
        val popup = PopupMenu(this, view)
        for (x in 0..PrefUtils.getCity()!!.size - 1) {
            popup.menu.add(0, city!!.get(x).id, 0, city!!.get(x).title)
        }
        popup.setOnMenuItemClickListener(
            PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
                cityText.setText(item!!.title)
                setCity(item.itemId.toString())
                true
            })
        popup.show()

    }
*/

    fun setCity(city: String) {
        this.cityValue = city
    }

    fun getCity(): String? {
        return cityValue
    }


    override fun onError(errorMessage: String, errorCode: Int) {
        showToast(errorMessage + errorCode.toString())

        //showError2(errorMessage)
    }

    override fun onSucces(message: String) {
        //showPopupSuccess(message)
    }

/*
    fun showError2(errorMessage: String) {
        val model = DialogUtils.DialogModel(
            "",
            errorMessage,
            0,
            "Tamam",
            "",
            R.drawable.ic_red_circle,
            false
        )

        DialogUtils.showAlertDialog(this, model, object : DialogUtils.DialogAlertListener {

            override fun onPositiveClick() {

            }

            override fun onNegativeClick() {

            }

        })
    }
*/
/*
    *//** Bilgilendirme mesajları*//*
    fun showPopupInfo(message: String) {
        myDialog = Dialog(this)
        myDialog?.setContentView(R.layout.custom_popup_info)
        val btnFollow: AppCompatButton = myDialog!!.findViewById(R.id.btnPopupInfo) as AppCompatButton
        val infoText: TextView = myDialog!!.findViewById(R.id.txtPopupInfo) as TextView
        val close: ConstraintLayout = myDialog!!.findViewById(R.id.clInfoClose) as ConstraintLayout
        infoText.setText(message)
        if (message.equals(resources.getString(R.string.popup_appointment_cancel))) {
            btnFollow.text = resources.getString(R.string.popup_agree)
        }
        btnFollow.setOnClickListener {
            //todo eğer "ertleme talebi başarılı ise" ertelenirse veri tabanından succes dönünce göster
            myDialog?.dismiss()
        }

        close.setOnClickListener {
            myDialog?.dismiss()
        }
        myDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog!!.show()
    }

    *//**Error mesajları*//*
    fun showPopupError(message: String) {

        showError2(message)

        *//* myDialog = Dialog(this)
         myDialog?.setContentView(R.layout.custom_popup_error)
         val txtError: TextView = myDialog!!.findViewById(R.id.txtPopupInfo) as TextView
         val btnFollow: AppCompatButton = myDialog!!.findViewById(R.id.btnPopupInfo) as AppCompatButton
         val close: ConstraintLayout = myDialog!!.findViewById(R.id.clPopup) as ConstraintLayout
         txtError.text = message
         btnFollow.setOnClickListener {

             myDialog?.dismiss()

         }

         close.setOnClickListener {
             myDialog?.dismiss()
         }

         //myDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
         myDialog!!.show()*//*
    }

    *//**Başarılı mesajlar*//*
    fun showPopupSuccess(message: String) {
        myDialog = Dialog(this)
        myDialog?.setContentView(com.mobillium.birebirdiyet.R.layout.custom_popup_success)
        val btnFollow: AppCompatButton =
            myDialog!!.findViewById(com.mobillium.birebirdiyet.R.id.btnPopupSuccess) as AppCompatButton
        val txtPopup: TextView = myDialog!!.findViewById(com.mobillium.birebirdiyet.R.id.txtPopupSucsess) as TextView
        val close: ConstraintLayout = myDialog!!.findViewById(R.id.clSuccessClose) as ConstraintLayout

        txtPopup.text = message

        btnFollow.setOnClickListener {
            myDialog?.dismiss()

        }

        close.setOnClickListener {
            myDialog?.dismiss()
        }

        myDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog!!.show()
    }*/

    /**Tarih işlemleri*/
    fun openDate(textView: TextView) {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(
            this,
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                var day: Int = dayOfMonth
                var month: Int = (monthOfYear + 1)
                var monthString: String = month.toString()
                var dayString: String = day.toString()

                if (month < 10) {
                    monthString = "0" + month.toString()
                }
                if (dayOfMonth < 10) {
                    dayString = "0" + day.toString()
                }
                textView.text = dayString + "." + monthString + "." + "$year"
                setDate("$year-$monthString-$dayString")

                //textView.setTextColor(this.resources.getColor(R.color.colorTextColor))

            },
            year,
            month,
            day
        )

        dpd.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dpd.show()

    }

    fun setDate(birth: String) {
        this.birthDateValue = birth
    }

    fun getDate(): String? {
        return birthDateValue
    }

    /**Fotoğraf seç*/

/*
    fun showPopupPhoto(imageView: ImageView) {
        this.photo = imageView
        myDialog = Dialog(this)
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
            val uri = saveImage(bitmap)
            setPath(uri.toString())
            multiPath(uri.toString(), photo)

        } else if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data?.data
            InputImageCompressor.compressInputImage(data, this,photo)
            //photo.setImageURI(uri)

            val filePath = FilePickUtils.getPath(this, uri!!)
            if (filePath != null) {
                setPath(filePath)
                multiPath(filePath, photo)


            }
        }

    }

    open fun multiPath(filePath: String, photo: ImageView) {

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

    fun chechkOption(option1: CheckBox, option2: CheckBox): Boolean {
        if (option1.isChecked == false && option2.isChecked == false) {
            return false
        } else {
            return true
        }

    }

    /*fun optionAppointment(
        option1: CheckBox,
        option2: CheckBox,
        option1Text: String,
        option2Text: String,
        pageNumber: Int
    ): String {
        option1.setText(option1Text)
        option2.setText(option2Text)
        option1.setOnClickListener(View.OnClickListener {
            if (option1.isChecked) {
                option1.setTextColor(getResources().getColor(R.color.green))
                option2.setTextColor(resources.getColor(R.color.colorTextColor))
                option2.isChecked = false
            } else {
                option1.setTextColor(resources.getColor(R.color.colorTextColor))
            }
        })
        option2.setOnClickListener(View.OnClickListener {
            if (option2.isChecked) {
                option2.setTextColor(getResources().getColor(R.color.green))
                option1.setTextColor(getResources().getColor(R.color.colorTextColor))
                option1.isChecked = false
            } else {
                option2.setTextColor(resources.getColor(R.color.colorTextColor))
            }

        })
        if (option1.isChecked)
            return "0"
        else
            return "1"
    }*/

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume")
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}

