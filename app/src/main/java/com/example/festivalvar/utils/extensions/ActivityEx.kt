package com.example.festivalvar.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.festivalvar.CoreApp
import com.example.festivalvar.R

fun AppCompatActivity.showToast(message: String){
    Toast.makeText(CoreApp.context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(message: String){
    Toast.makeText(CoreApp.context, message, Toast.LENGTH_LONG).show()
}


fun AppCompatActivity.overridePendingTransitionEnter() {
    overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_exit)
}


fun AppCompatActivity.overridePendingTransitionExit() {
    overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_exit)
}