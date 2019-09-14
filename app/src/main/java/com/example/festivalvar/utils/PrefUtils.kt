package com.example.festivalvar.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.festivalvar.CoreApp
import com.example.festivalvar.data.remote.model.user.User
import com.example.festivalvar.utils.AppConstants.IS_USER_LOGGED
import com.example.festivalvar.utils.AppConstants.PREF_NAME
import com.example.festivalvar.utils.AppConstants.TOKEN
import com.example.festivalvar.utils.AppConstants.USER_DETAIL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mobillium.birebirdiyet.utils.extensions.get
import com.mobillium.birebirdiyet.utils.extensions.removeValue
import com.mobillium.birebirdiyet.utils.extensions.setValue
import java.lang.reflect.Type

object PrefUtils {
    private const val FIRST_TIME = "is_First_Time_Open"
    private const val FIRST_TIME_INFO = "is_First_Time_Info"


    private val instance: SharedPreferences = CoreApp.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val questionsList = ArrayList<String>()

    fun checkIsFirstTimeOpen(): Boolean {
        val firstTime = instance.getBoolean(FIRST_TIME, true)
        if (firstTime) {
            instance.setValue(FIRST_TIME, false)
            return true
        }
        return false
    }

    fun isLoggedUser(): Boolean = instance.getBoolean(IS_USER_LOGGED, false)


    fun createToken(token: String) {
        instance.setValue(TOKEN, token)
    }


    fun set(user: String) {

        instance.removeValue(USER_DETAIL)
        instance.setValue(USER_DETAIL, user)

    }


    fun createUser(user: String) {

        if (!isLoggedUser()) {

            instance.setValue(IS_USER_LOGGED, true)

        }
        instance.removeValue(USER_DETAIL)
        instance.setValue(USER_DETAIL, user)


    }

    fun getUser(): User {

        return GsonBuilder().create().fromJson(instance.getString(USER_DETAIL, ""), User::class.java)

    }


    fun getToken(): String {
        if (instance.getString(TOKEN, null).isNullOrEmpty()) {
            return ""
        } else {
            return GsonBuilder().create().fromJson(instance.getString(TOKEN, ""), String::class.java)
        }
    }

    fun logoutUser() {

        instance.setValue(IS_USER_LOGGED, false)

        instance.removeValue(USER_DETAIL)

        instance.removeValue(TOKEN)

    }



}