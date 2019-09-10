package com.example.festivalvar.utils

import com.example.festivalvar.BuildConfig

object AppConstants {
    const val DELAY_TIME: Long = 1000
    const val API_URL = BuildConfig.BASE_URL
    const val REQUEST_TIMEOUT: Long = 60
    const val DB_NAME = "birebirdiyet.db"
    const val PREF_NAME = "birebirdiyetpref"

    const val APPOINTMENT_FROM_NAVIGATION = "appointment_form_navigation"
    const val APPOINTMENT_FROM_NUTRITIONIST = "appointment_form_nutritionist"


    val MAIN_FRAGMENT = "MAIN_FRAGMENT"

    val FIRST = true

    const val FIRST_TIME = "is_First_Time_Open"


    const val APPOINTMENT_FROM_CV_NUTRITIONIST = "appointment_form_cv_nutritionist"
    const val APPOINTMENT_FROM_CV_SERVICE = "appointment_form_cv_service"
    const val APPOINMENT_SCREEN = "appoinment_screen"
    const val CLINIC_DETAIL_SCREEN = "clinic_detail_screen"
    const val SERVICE_FROM_CLINIC_DETAIL = "service_from_clinic_detail"
    const val VERIFY_FROM_NEW_PHONE = "verify_from_new_phone"
    const val VERIFY_SCREEN = "verify_screen"
    const val VERIFY_FROM_REGISTER = "verify_from_register"
    const val NUTRITIONIST_SCREEN = "verify_from_register"
    const val MAIN_SCREEN_FROM_NUTRTIONIST = "verify_from_register"
    const val IS_USER_LOGGED = "is_user_logged"
    const val USER_DETAIL = "user_detail"
    const val TOKEN = "token"
    const val CITY = "city"

    /**Form sayfası yönlendirmesi*/
    var DIETICIAN_DIRECTON = "DETAILS"
    const val APPOINTMENTFORM = "APPOINTMENTFORM"


    const val REGISTER_TO_CONTRACT = "CONTRACT"
    const val CONTRACT_TO_CONTRACT_DETAIL = "CONTRACT"

    var TAB_VALUE = "DEFAULT"
    const val ONLINE = "ONLINE"
    const val CLINIC = "CLINIC"
    var MAIN_TAB_VALUE = "DEFAULT"
    const val ADD_MEAL = "ADD_MEAL"

    /*photo tag*/
    const val NOT_NULL = "NOT_NULL"
    const val PHOTO_NULL = "PHOTO_NULL"

}