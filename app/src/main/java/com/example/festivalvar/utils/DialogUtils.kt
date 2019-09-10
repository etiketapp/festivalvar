package com.example.festivalvar.utils

import androidx.annotation.DrawableRes

object DialogUtils {

    data class DialogModel(

        var title: String? = null,

        var message: String? = null,

        var uniqueId: Int = 0,

        var positive: String? = null,

        var negative: String? = null,

        @DrawableRes

        var icon: Int = 0,

        var isNegativeButton: Boolean = false

    )


    interface DialogAlertListener {

        fun onPositiveClick()

        fun onNegativeClick()

    }

/*
    fun showAlertDialog(

        context: Context,

        dialogModel: DialogModel,

        listener: DialogAlertListener? = null

    ) {

        val dialog = AlertDialog.Builder(context)

        val layoutInflater = LayoutInflater.from(context)

        val dialogView = layoutInflater.inflate(R.layout.custom_popup_error, null)

        val icon = dialogView.findViewById<ConstraintLayout>(R.id.clPopup)

        val message = dialogView.findViewById<TextView>(R.id.txtPopupInfo)

        val btnOk = dialogView.findViewById<ConstraintLayout>(R.id.clPopup)

        val btnPositive = dialogView.findViewById<Button>(R.id.btnPopupInfo)

        //val btnNegative = dialogView.findViewById<AppCompatButton>(R.id.btnNegativeDialog)

        //val lnNegative = dialogView.findViewById<LinearLayout>(R.id.lnNegative)

        dialog.setView(dialogView)


        //icon.setImageDrawable(ContextCompat.getDrawable(context, dialogModel.icon))

        message.text = dialogModel.message





        if (dialogModel.isNegativeButton) {

            //lnNegative.visibility = View.VISIBLE

            btnOk.visibility = View.GONE

            btnPositive.text = dialogModel.positive

            //btnNegative.text = dialogModel.negative

        } else {

            //lnNegative.visibility = View.GONE

            btnOk.visibility = View.VISIBLE

            // btnOk.text = dialogModel.positive

        }


        val alertDialog = dialog.create()

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        alertDialog.setCanceledOnTouchOutside(false)

        val v = alertDialog.window.decorView

        v.setBackgroundResource(android.R.color.transparent)

        alertDialog.show()



        btnOk.setOnClickListener {

            listener?.onPositiveClick()

            alertDialog.cancel()

        }



        btnPositive.setOnClickListener {

            listener?.onPositiveClick()

            alertDialog.cancel()

        }


*//*
        btnNegative.setOnClickListener {

            listener?.onNegativeClick()

            alertDialog.cancel()

        }*//*


    }*/
/*
    fun showAlertConfirmationDialog(

        context: Context,

        dialogModel: DialogModel,

        listener: DialogAlertListener? = null

    ) {

        val dialog = AlertDialog.Builder(context)

        val layoutInflater = LayoutInflater.from(context)

        val dialogView = layoutInflater.inflate(R.layout.custom_popup_login_register, null)

        val icon = dialogView.findViewById<ImageView>(R.id.imgPopupError)

        val message = dialogView.findViewById<TextView>(R.id.txtPopupSucsess)

        val btnNegative = dialogView.findViewById<AppCompatButton>(R.id.btnDecline)

        val btnPositive = dialogView.findViewById<AppCompatButton>(R.id.btnAccept)

        val cancelButton=dialogView.findViewById<ConstraintLayout>(R.id.clSuccessClose)




        dialog.setView(dialogView)

        icon.setImageDrawable(ContextCompat.getDrawable(context, dialogModel.icon))


        message.text = dialogModel.message


        btnPositive.text = dialogModel.positive

        btnNegative.text = dialogModel.negative


        if (dialogModel.isNegativeButton) {


            btnNegative.visibility = View.VISIBLE
            btnPositive.visibility = View.VISIBLE


        } else {


            btnNegative.visibility = View.GONE


        }


        val alertDialog = dialog.create()

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        alertDialog.setCanceledOnTouchOutside(false)

        val v = alertDialog.window.decorView

        v.setBackgroundResource(android.R.color.transparent)

        alertDialog.show()



        btnNegative.setOnClickListener {

            listener?.onNegativeClick()

            alertDialog.cancel()

        }



        btnPositive.setOnClickListener {

            listener?.onPositiveClick()

            alertDialog.cancel()

        }


        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }


    }*/

/*
    fun checkLoginControl(context: Context) {

        val dialog = AlertDialog.Builder(context)

        val layoutInflater = LayoutInflater.from(context)

        val dialogView = layoutInflater.inflate(R.layout.custom_popup_login_register, null)

        val icon = dialogView.findViewById<ImageView>(R.id.imgPopupError)

        val message = dialogView.findViewById<TextView>(R.id.txtPopupSucsess)

        val btnNegative = dialogView.findViewById<AppCompatButton>(R.id.btnDecline)

        val btnPositive = dialogView.findViewById<AppCompatButton>(R.id.btnAccept)

        val cancelButton=dialogView.findViewById<ConstraintLayout>(R.id.clSuccessClose)


        message.setText(R.string.popu_login_register)

        icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info))

        dialog.setView(dialogView)

        val alertDialog = dialog.create()

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        alertDialog.setCanceledOnTouchOutside(false)

        val v = alertDialog.window.decorView

        v.setBackgroundResource(android.R.color.transparent)

        alertDialog.show()

*/
/*

        btnNegative.setOnClickListener {
            alertDialog.cancel()
            alertDialog.dismiss()

        }

        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }


        btnPositive.setOnClickListener {
            alertDialog.cancel()


        }*//*



    }
*/


}

